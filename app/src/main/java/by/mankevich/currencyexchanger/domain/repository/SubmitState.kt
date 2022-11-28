package by.mankevich.currencyexchanger.domain.repository

import by.mankevich.currencyexchanger.domain.entity.Money

sealed class SubmitState(
    val type: String,
    val sellMoney: Money? = null,
    val receiveMoney: Money? = null,
    val fee: Money? = null,
    val storageSellBalance: Money? = null
) {
    class Success(
        sellMoney: Money,
        receiveMoney: Money,
        fee: Money,
    ) : SubmitState(
        type = "Success",
        sellMoney = sellMoney,
        receiveMoney = receiveMoney,
        fee = fee,
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
}