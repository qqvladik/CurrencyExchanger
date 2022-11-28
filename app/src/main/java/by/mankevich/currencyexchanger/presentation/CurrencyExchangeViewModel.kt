package by.mankevich.currencyexchanger.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.mankevich.currencyexchanger.core.presentation.BaseViewModel
import by.mankevich.currencyexchanger.domain.entity.Money
import by.mankevich.currencyexchanger.domain.repository.CurrencyExchangeRepository
import by.mankevich.currencyexchanger.domain.repository.SubmitState
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val DEFAULT_CURRENCY_EUR_TYPE = "EUR"
private const val DEFAULT_CURRENCY_EUR_AMOUNT = 1000.0

class CurrencyExchangeViewModel @Inject constructor(
    private val repository: CurrencyExchangeRepository
) : BaseViewModel() {

    var sellAmount: Double? = 0.0
        set(value) {
            field = value
            calculateReceiveAmount()
        }

    var sellCurrencyType: String? = null
        set(value) {
            field = value
            calculateReceiveAmount()
        }

    var receiveCurrencyType: String? = null
        set(value) {
            field = value
            calculateReceiveAmount()
        }

    private val _balances = MutableLiveData<List<Money>>()
    val balances: LiveData<List<Money>>
        get() = _balances

    private val _currencyTypes = MutableLiveData<List<String>>()
    val currencyTypes: LiveData<List<String>>
        get() = _currencyTypes

    private val _receiveAmount = MutableLiveData(0.0)
    val receiveAmount: LiveData<Double>
        get() = _receiveAmount

    private val _submitState = MutableLiveData<SubmitState>()
    val submitState: LiveData<SubmitState>
        get() = _submitState

    init {
        setDefaultBalance()
        fetchBalances()
        fetchCurrencyRates()
        fetchCurrencyTypes()
    }

    private fun setDefaultBalance() {
        viewModelScope.launch {
            if (repository.isBalancesEmpty()) {
                repository.saveBalance(
                    Money(
                        DEFAULT_CURRENCY_EUR_TYPE,
                        DEFAULT_CURRENCY_EUR_AMOUNT
                    )
                )
            }
        }
    }

    private fun fetchBalances() {
        viewModelScope.launch {
            repository.getAllBalances().collect { balanceList ->
                _balances.value = balanceList
            }
        }
    }

    private fun fetchCurrencyRates() {
        viewModelScope.launch {
            repository.fetchCurrencyRates()
        }
    }

    private fun fetchCurrencyTypes() {
        viewModelScope.launch {
            val currencyTypesFlow = repository.getAllCurrencyTypes()
            currencyTypesFlow.collect { currencyTypes ->
                if (currencyTypes.isNotEmpty()) {
                    _currencyTypes.value = currencyTypes
                }
            }

        }
    }

    private fun calculateReceiveAmount() {
        if (sellAmount == 0.0 || sellCurrencyType == null || receiveCurrencyType == null) return
        viewModelScope.launch {
            _receiveAmount.value = repository.calculateReceiveAmount(
                sellAmount!!,
                sellCurrencyType!!,
                receiveCurrencyType!!
            )
        }
    }

    fun onSubmitExchange() {
        viewModelScope.launch {
            val sellMoney = Money(sellCurrencyType!!, sellAmount!!)
            val receiveMoney = Money(receiveCurrencyType!!, _receiveAmount.value!!)
            _submitState.value = repository.submitExchange(sellMoney, receiveMoney)
        }
    }
}
