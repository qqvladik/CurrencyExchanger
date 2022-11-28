package by.mankevich.currencyexchanger.data.repository

import by.mankevich.currencyexchanger.data.db.UserDao
import by.mankevich.currencyexchanger.domain.entity.Money
import by.mankevich.currencyexchanger.domain.repository.CommissionCalculator

private const val COMMISSION = 0.007
const val USER_NAME_MANKEVICH = "Mankevich"
const val COMMISSION_FREE_AMOUNT = 0.0

class CommissionCalculatorImpl(
    private val userDao: UserDao
) : CommissionCalculator {

    override suspend fun calcCommission(sellMoney: Money): Money {
        val user = userDao.getUser(USER_NAME_MANKEVICH)
        val commissionAmount = if (user.counterFreeCommission >= 5) {
            COMMISSION * sellMoney.amount
        } else {
            COMMISSION_FREE_AMOUNT
        }
        user.counterFreeCommission++
        userDao.insertUser(user)
        return Money(sellMoney.currencyType, commissionAmount)
    }
}
