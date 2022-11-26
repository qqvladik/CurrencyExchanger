package by.mankevich.currencyexchanger.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.mankevich.currencyexchanger.R
import by.mankevich.currencyexchanger.databinding.ActivityMainBinding
import by.mankevich.currencyexchanger.domain.entity.Balance
import by.mankevich.currencyexchanger.utils.SpacingItemDecorator

class CurrencyExchangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
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

}
