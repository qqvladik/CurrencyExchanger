package by.mankevich.currencyexchanger.data.api.response

import by.mankevich.currencyexchanger.domain.entity.CurrencyRate

data class CurrencyExchangeRatesResponse (
    val base: String,
    val date: String,
    val rates: List<CurrencyRate>
)
