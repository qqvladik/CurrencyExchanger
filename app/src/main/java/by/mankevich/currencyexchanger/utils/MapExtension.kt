package by.mankevich.currencyexchanger.utils

import by.mankevich.currencyexchanger.domain.entity.CurrencyRate

fun Map<String, Double>.parseToCurrencyRateList(): List<CurrencyRate> {
    return this.map { entryCurrencyRate->
        CurrencyRate(entryCurrencyRate.key, entryCurrencyRate.value)
    }
}
