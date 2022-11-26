package by.mankevich.currencyexchanger.data.api

import by.mankevich.currencyexchanger.data.api.response.CurrencyExchangeRatesResponse
import retrofit2.http.GET

interface CurrencyExchangeApi {

    @GET("currency-exchange-rates")
    suspend fun fetchCurrencyRates(): CurrencyExchangeRatesResponse
}
