package by.mankevich.currencyexchanger.utils

import by.mankevich.currencyexchanger.domain.entity.Money
import java.lang.Exception


operator fun Money.plus(money: Money): Money {
    if (this.currencyType == money.currencyType) {
        return apply { amount += money.amount }
    } else {
        throw Exception("Balances have different currencyTypes!")
    }
}

operator fun Money.minus(money: Money): Money {
    if (this.currencyType == money.currencyType) {
        return apply { amount -= money.amount }
    } else {
        throw Exception("Balances have different currencyTypes!")
    }
}

fun Money.amountAndCurrencyText(): String{
    return String.format("%1\$.2f %2\$s", amount, currencyType)
}
