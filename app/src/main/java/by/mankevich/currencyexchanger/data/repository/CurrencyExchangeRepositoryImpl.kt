package by.mankevich.currencyexchanger.data.repository

import by.mankevich.currencyexchanger.data.api.CurrencyExchangeApi
import by.mankevich.currencyexchanger.data.db.BalanceDao
import by.mankevich.currencyexchanger.data.db.CurrencyRateDao
import by.mankevich.currencyexchanger.domain.entity.Balance
import by.mankevich.currencyexchanger.domain.entity.CurrencyRate
import by.mankevich.currencyexchanger.domain.repository.CurrencyExchangeRepository
import by.mankevich.currencyexchanger.utils.parseToCurrencyRateList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyExchangeRepositoryImpl(
    private val currencyExchangeApi: CurrencyExchangeApi,
    private val balanceDao: BalanceDao,
    private val currencyRateDao: CurrencyRateDao
) : CurrencyExchangeRepository {

    override suspend fun fetchAndSaveCurrencyRates(): List<CurrencyRate> {
        val currencyRateList = fetchCurrencyRates()
        saveCurrencyRates(currencyRateList)
        return currencyRateList
    }

    private suspend fun fetchCurrencyRates(): List<CurrencyRate> {
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

    override suspend fun getRate(type: String): Double {
        return currencyRateDao.getCurrencyRate(type).rate
    }

    override suspend fun saveBalance(balance: Balance) {
        balanceDao.insertBalance(balance)
    }

    override fun getAllBalances(): Flow<List<Balance>> {
        return balanceDao.getAllBalances()
    }

    override fun getBalance(currencyType: String): Balance? {
        return balanceDao.getBalance(currencyType)
    }
}
