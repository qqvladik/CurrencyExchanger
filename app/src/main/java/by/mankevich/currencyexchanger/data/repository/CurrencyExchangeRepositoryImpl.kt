package by.mankevich.currencyexchanger.data.repository

import by.mankevich.currencyexchanger.data.api.CurrencyExchangeApi
import by.mankevich.currencyexchanger.data.api.response.CurrencyExchangeRatesResponse
import by.mankevich.currencyexchanger.data.db.BalanceDao
import by.mankevich.currencyexchanger.data.db.CurrencyDao
import by.mankevich.currencyexchanger.domain.repository.CurrencyExchangeRepository

class CurrencyExchangeRepositoryImpl(
    private val currencyExchangeApi: CurrencyExchangeApi,
    private val balanceDao: BalanceDao,
    private val currencyDao: CurrencyDao
) : CurrencyExchangeRepository {

    override suspend fun fetchRates(): CurrencyExchangeRatesResponse{
        return currencyExchangeApi.fetchCurrencyRates()
    }
}
