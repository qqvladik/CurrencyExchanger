package by.mankevich.currencyexchanger.data.repository

import by.mankevich.currencyexchanger.data.api.CurrencyExchangeApi
import by.mankevich.currencyexchanger.data.db.BalanceDao
import by.mankevich.currencyexchanger.data.db.CurrencyRateDao
import by.mankevich.currencyexchanger.data.db.UserDao
import by.mankevich.currencyexchanger.domain.entity.Money
import by.mankevich.currencyexchanger.domain.entity.CurrencyRate
import by.mankevich.currencyexchanger.domain.entity.User
import by.mankevich.currencyexchanger.domain.repository.CommissionCalculator
import by.mankevich.currencyexchanger.domain.repository.CurrencyExchangeRepository
import by.mankevich.currencyexchanger.domain.repository.SubmitState
import by.mankevich.currencyexchanger.utils.minus
import by.mankevich.currencyexchanger.utils.parseToCurrencyRateList
import by.mankevich.currencyexchanger.utils.plus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

open class CurrencyExchangeRepositoryImpl(
    private val currencyExchangeApi: CurrencyExchangeApi,
    private val balanceDao: BalanceDao,
    private val currencyRateDao: CurrencyRateDao,
    private val userDao: UserDao,
    private val commissionCalculator: CommissionCalculator
) : CurrencyExchangeRepository {

    override suspend fun fetchCurrencyRates(): List<CurrencyRate> {
        val currencyRateList = getCurrencyRates()
        saveCurrencyRates(currencyRateList)
        return currencyRateList
    }

    private suspend fun getCurrencyRates(): List<CurrencyRate> {
        return currencyExchangeApi.fetchCurrencyRates().rates.parseToCurrencyRateList()
    }

    private suspend fun saveCurrencyRates(currencyRateList: List<CurrencyRate>) {
        currencyRateDao.insertCurrencyRates(currencyRateList)
    }

    override fun getAllCurrencyTypes(): Flow<List<String>> {
        return currencyRateDao.getAllCurrencyRates().map { currencyRateList ->
            currencyRateList.map { currencyRate ->
                currencyRate.type
            }
        }
    }

    override suspend fun isBalancesEmpty(): Boolean {
        return balanceDao.isEmpty()
    }

    override suspend fun saveBalance(balance: Money) {
        balanceDao.insertBalance(balance)
    }

    override fun getAllBalances(): Flow<List<Money>> {
        return balanceDao.getAllBalances()
    }

    override suspend fun getBalance(currencyType: String): Money? {
        return balanceDao.getBalance(currencyType)
    }

    override suspend fun isUsersEmpty(): Boolean {
        return userDao.isEmpty()
    }

    override suspend fun saveUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun calculateReceiveAmount(
        sellAmount: Double,
        receiveCurrencyType: String,
        sellCurrencyType: String
    ): Double {
        val receiveRate = getRate(receiveCurrencyType)
        val sellRate = getRate(sellCurrencyType)
        return sellAmount * sellRate / receiveRate
    }

    private suspend fun getRate(type: String): Double {
        return currencyRateDao.getCurrencyRate(type).rate
    }

    override suspend fun submitExchange(
        sellMoney: Money,
        receiveMoney: Money
    ): SubmitState {
        if (sellMoney.currencyType == receiveMoney.currencyType) {
            return SubmitState.SameType(sellMoney)
        }

        val storageSellBalance =
            getBalance(sellMoney.currencyType) ?: return SubmitState.NoType(sellMoney)

        val commission = commissionCalculator.calcCommission(
            sellMoney
        )
        val commissionWithSellMoney = sellMoney + commission

        if (storageSellBalance.amount < (commissionWithSellMoney).amount) {
            return SubmitState.SmallAmount(commissionWithSellMoney, storageSellBalance)
        }

        saveBalance(storageSellBalance - commissionWithSellMoney)
        val storageReceiveBalance = getBalance(receiveMoney.currencyType)
        if (storageReceiveBalance == null) {
            saveBalance(receiveMoney)
        } else {
            saveBalance(storageReceiveBalance + receiveMoney)
        }
        return SubmitState.Success(sellMoney, receiveMoney, commission)
    }
}

