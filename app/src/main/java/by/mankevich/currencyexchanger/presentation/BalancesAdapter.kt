package by.mankevich.currencyexchanger.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import by.mankevich.currencyexchanger.core.presentation.BaseAdapter
import by.mankevich.currencyexchanger.core.presentation.BaseViewHolder
import by.mankevich.currencyexchanger.databinding.BalanceItemBinding
import by.mankevich.currencyexchanger.domain.entity.Balance

class BalancesAdapter(
    balances: List<Balance>,
    private var onItemClick: (Balance) -> Unit = {}
) :
    BaseAdapter<Balance, BalanceItemBinding, BalancesAdapter.BalanceViewHolder>(
        balances
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalanceViewHolder{
        val binding = BalanceItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BalanceViewHolder(binding, onItemClick)
    }

    inner class BalanceViewHolder(
        binding: BalanceItemBinding,
        onItemClick: (Balance) -> Unit
    ) :
        BaseViewHolder<Balance, BalanceItemBinding>(
            binding, onItemClick
        ) {

        override fun bindView(entity: Balance) {
            binding.balanceItemText.text = entity.amount.toString()
                .plus(" ")
                .plus(entity.currency)
        }
    }

}