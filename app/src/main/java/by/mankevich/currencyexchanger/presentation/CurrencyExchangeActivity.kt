package by.mankevich.currencyexchanger.presentation

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.mankevich.currencyexchanger.CurrencyExchangerApp
import by.mankevich.currencyexchanger.core.presentation.BaseActivity
import by.mankevich.currencyexchanger.databinding.CurrencyExchangeActivityBinding
import by.mankevich.currencyexchanger.domain.entity.Balance
import by.mankevich.currencyexchanger.utils.SpacingItemDecorator

class CurrencyExchangeActivity :
    BaseActivity<CurrencyExchangeActivityBinding, CurrencyExchangeViewModel>(
        CurrencyExchangeViewModel::class.java
    ) {

    override fun initDaggerComponent() {
        (application as CurrencyExchangerApp).appComponent.injectActivity(this)
    }

    override fun initBinding(): CurrencyExchangeActivityBinding {
        return CurrencyExchangeActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        observeCurrencyTypes()
    }

    private fun initViews() {
        binding.balanceContainer.apply {
            layoutManager = LinearLayoutManager(
                this@CurrencyExchangeActivity,
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = BalancesAdapter(
                listOf()
            )
            addItemDecoration(SpacingItemDecorator(25))
        }
    }

    private fun observeCurrencyTypes() {
        viewModel.currencyTypes.observe(this) { currencyTypes ->
            val adapterCurrencyTypes = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                currencyTypes
            )
            binding.cellCurrencyTypesSpinner.adapter = adapterCurrencyTypes
            binding.receiveCurrencyTypesSpinner.adapter = adapterCurrencyTypes
        }
    }
}
