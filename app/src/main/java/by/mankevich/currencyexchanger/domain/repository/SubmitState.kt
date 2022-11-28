package by.mankevich.currencyexchanger.domain.repository

import by.mankevich.currencyexchanger.domain.entity.Money

sealed class SubmitState(
    val type: String,
    val sellMoney: Money? = null,
    val receiveMoney: Money? = null,
    val commission: Money? = null,
    val storageSellBalance: Money? = null
) {
    class Success(
        sellMoney: Money,
        receiveMoney: Money,
        commission: Money,
    ) : SubmitState(
        type = "Currency converted",
        sellMoney = sellMoney,
        receiveMoney = receiveMoney,
        commission = commission,
        )

    class SmallAmount(
        sellMoney: Money,
        storageSellBalance: Money,
    ) : SubmitState(
        type = "Error",
        sellMoney = sellMoney,
        storageSellBalance = storageSellBalance
    )

    class NoType(
        sellMoney: Money
    ) : SubmitState(
        type = "Error",
        sellMoney = sellMoney
    )

    class SameType(
        sellMoney: Money
    ) : SubmitState(
        type = "Error",
        sellMoney = sellMoney
    )
}