package by.mankevich.currencyexchanger.domain.repository

import by.mankevich.currencyexchanger.data.api.response.CurrencyExchangeRatesResponse

interface CurrencyExchangeRepository {
    suspend fun fetchRates(): CurrencyExchangeRatesResponse //TODO переделать
}
