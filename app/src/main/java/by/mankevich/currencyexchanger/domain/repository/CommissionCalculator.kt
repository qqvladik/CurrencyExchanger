package by.mankevich.currencyexchanger.domain.repository

import by.mankevich.currencyexchanger.domain.entity.Money

interface CommissionCalculator {

    suspend fun calcCommission(sellMoney: Money): Money
}
