package by.mankevich.currencyexchanger.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import by.mankevich.currencyexchanger.core.presentation.BaseAdapter
import by.mankevich.currencyexchanger.core.presentation.BaseViewHolder
import by.mankevich.currencyexchanger.databinding.BalanceItemBinding
import by.mankevich.currencyexchanger.domain.entity.Money
import by.mankevich.currencyexchanger.utils.amountAndCurrencyText

class BalancesAdapter(
    balances: List<Money> = listOf(),
    private var onItemClick: (Money) -> Unit = {}
) :
    BaseAdapter<Money, BalanceItemBinding, BalancesAdapter.BalanceViewHolder>(
        balances
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalanceViewHolder{
        val binding = BalanceItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BalanceViewHolder(binding, onItemClick)
    }

    inner class BalanceViewHolder(
        binding: BalanceItemBinding,
        onItemClick: (Money) -> Unit
    ) :
        BaseViewHolder<Money, BalanceItemBinding>(
            binding, onItemClick
        ) {

        override fun bindView(entity: Money) {
            binding.balanceItemText.text = entity.amountAndCurrencyText()

        }
    }

}