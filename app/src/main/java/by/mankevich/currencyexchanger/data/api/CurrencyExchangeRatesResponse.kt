package by.mankevich.currencyexchanger.data.api

data class CurrencyExchangeRatesResponse (
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
