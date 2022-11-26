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

class CurrencyExchangeActivity : BaseActivity<CurrencyExchangeActivityBinding, CurrencyExchangeViewModel>(
    CurrencyExchangeViewModel::class.java
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        viewModel.response.observe(this){ response->
            Log.d("My", "response: $response")//TODO убрать
        }
    }

    private fun initViews() {
        binding.balanceContainer.apply {
            layoutManager = LinearLayoutManager(
                this@CurrencyExchangeActivity,
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = BalancesAdapter(
                listOf(
                    Balance("EUR", 1000.0),
                    Balance("EUR", 1000.0),
                    Balance("EUR", 1000.0),
                    Balance("EUR", 1000.0),
                    Balance("EUR", 1000.0),
                )//TODO
            )
            addItemDecoration(SpacingItemDecorator(25))
        }

        val adapterCurrencyTypes = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf("EUR",
                "USD",
                "USD",
                "USD",
                "RUB")//TODO
        )
        binding.cellCurrencyTypesSpinner.adapter = adapterCurrencyTypes
        binding.receiveCurrencyTypesSpinner.adapter = adapterCurrencyTypes
    }

    override fun initDaggerComponent(){
        (application as CurrencyExchangerApp).appComponent.injectActivity(this)
    }

    override fun initBinding(): CurrencyExchangeActivityBinding {
        return CurrencyExchangeActivityBinding.inflate(layoutInflater)
    }
}
