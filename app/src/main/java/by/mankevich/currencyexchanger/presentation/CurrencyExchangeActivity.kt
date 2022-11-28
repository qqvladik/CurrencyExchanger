package by.mankevich.currencyexchanger.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.mankevich.currencyexchanger.CurrencyExchangerApp
import by.mankevich.currencyexchanger.R
import by.mankevich.currencyexchanger.core.presentation.BaseActivity
import by.mankevich.currencyexchanger.databinding.CurrencyExchangeActivityBinding
import by.mankevich.currencyexchanger.domain.repository.SubmitState
import by.mankevich.currencyexchanger.utils.SpacingItemDecorator
import by.mankevich.currencyexchanger.utils.amountAndCurrencyText
import com.google.android.material.dialog.MaterialAlertDialogBuilder


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
        setupEditTextListener()
        setupSellCurrencyTypesSpinnerListener()
        setupReceiveCurrencyTypesSpinnerListener()
        setupSubmitButtonListener()

        observeBalances()
        observeCurrencyTypes()
        observeReceiveAmount()
        observeSubmitState()
    }

    private fun initViews() {
        binding.balanceContainer.apply {
            layoutManager = LinearLayoutManager(
                this@CurrencyExchangeActivity,
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = BalancesAdapter()
            addItemDecoration(SpacingItemDecorator(25))
        }
    }

    private fun setupEditTextListener() {
        binding.sellAmountEditText.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                viewModel.sellAmount = 0.0
            } else {
                viewModel.sellAmount = text.toString().toDouble()
            }
        }
    }

    private fun setupSellCurrencyTypesSpinnerListener() {
        binding.sellCurrencyTypesSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                viewModel.sellCurrencyType =
                    binding.sellCurrencyTypesSpinner.selectedItem.toString()

            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                return
            }
        }
    }

    private fun setupReceiveCurrencyTypesSpinnerListener() {
        binding.receiveCurrencyTypesSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                viewModel.receiveCurrencyType =
                    binding.receiveCurrencyTypesSpinner.selectedItem.toString()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                return
            }
        }
    }

    private fun setupSubmitButtonListener() {
        binding.submitButton.setOnClickListener {
            viewModel.onSubmitExchange()
        }
    }

    private fun observeBalances() {
        viewModel.balances.observe(this) { balances ->
            (binding.balanceContainer.adapter as BalancesAdapter).setData(balances)
        }
    }

    private fun observeCurrencyTypes() {
        viewModel.currencyTypes.observe(this) { currencyTypes ->
            val adapterCurrencyTypes = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                currencyTypes
            )
            binding.sellCurrencyTypesSpinner.adapter = adapterCurrencyTypes
            binding.receiveCurrencyTypesSpinner.adapter = adapterCurrencyTypes
        }
    }

    private fun observeReceiveAmount() {
        viewModel.receiveAmount.observe(this) { receiveAmount ->
            binding.receiveAmountText.text = receiveAmount.toString()
                String.format(
                getString(R.string.receive_amount_format_text),
                receiveAmount
            )
        }
    }

    private fun observeSubmitState() {
        viewModel.submitState.observe(this) { submitState ->
            MaterialAlertDialogBuilder(this).apply {
                val message: String = when (submitState) {
                    is SubmitState.Success -> String.format(
                        getString(R.string.submit_success_message_format_text),
                        submitState.sellMoney!!.amountAndCurrencyText(),
                        submitState.receiveMoney!!.amountAndCurrencyText(),
                        submitState.fee!!.amountAndCurrencyText()
                    )
                    is SubmitState.SmallAmount -> String.format(
                        getString(R.string.submit_small_amount_message_format_text),
                        submitState.storageSellBalance!!.amountAndCurrencyText(),
                        submitState.sellMoney!!.amountAndCurrencyText()
                    )
                    is SubmitState.NoType -> String.format(
                        getString(R.string.submit_no_type_message_format_text),
                        submitState.sellMoney!!.currencyType
                    )
                }
                setTitle(submitState.type)
                setMessage(message)
                setPositiveButton(resources.getString(R.string.dialog_positive_button_text)) { dialog, _ ->
                    dialog.dismiss()
                }
                show()
            }
        }
    }
}
