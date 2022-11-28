package by.mankevich.currencyexchanger.domain.repository

import by.mankevich.currencyexchanger.domain.entity.Money
import by.mankevich.currencyexchanger.domain.entity.CurrencyRate
import by.mankevich.currencyexchanger.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface CurrencyExchangeRepository {

    suspend fun fetchCurrencyRates(): List<CurrencyRate>
    fun getAllCurrencyTypes(): Flow<List<String>>

    suspend fun isBalancesEmpty(): Boolean
    suspend fun saveBalance(balance: Money)
    fun getAllBalances(): Flow<List<Money>>
    suspend fun getBalance(currencyType: String): Money?

    suspend fun isUsersEmpty(): Boolean
    suspend fun saveUser(user: User)

    suspend fun calculateReceiveAmount(
        sellAmount: Double,
        receiveCurrencyType: String,
        sellCurrencyType: String
    ): Double

    suspend fun submitExchange(sellMoney: Money, receiveMoney: Money): SubmitState
}
