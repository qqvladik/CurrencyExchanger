package by.mankevich.currencyexchanger.domain.repository

import by.mankevich.currencyexchanger.domain.entity.Balance
import by.mankevich.currencyexchanger.domain.entity.CurrencyRate
import kotlinx.coroutines.flow.Flow

interface CurrencyExchangeRepository {

    suspend fun fetchAndSaveCurrencyRates(): List<CurrencyRate>
    fun getAllCurrencyTypes(): Flow<List<String>>
    suspend fun getRate(type: String): Double

    suspend fun saveBalance(balance: Balance)
    fun getAllBalances(): Flow<List<Balance>>
    fun getBalance(currencyType: String): Balance?
}
