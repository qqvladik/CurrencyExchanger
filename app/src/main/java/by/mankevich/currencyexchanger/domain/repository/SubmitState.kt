package by.mankevich.currencyexchanger.domain.repository

import by.mankevich.currencyexchanger.domain.entity.Money

sealed class SubmitState(
    val type: String,
) {
    data class Success(
        val sellMoney: Money,
        val receiveMoney: Money,
        val commission: Money,
    ) : SubmitState(
        type = "Currency converted"
    )

    data class SmallAmount(
        val sellMoney: Money,
        val storageSellBalance: Money,
    ) : SubmitState(
        type = "Error",
    )

    data class NoType(
        val sellMoney: Money
    ) : SubmitState(
        type = "Error",
    )

    data class SameType(
        val sellMoney: Money
    ) : SubmitState(
        type = "Error",
    )
}
