package by.mankevich.currencyexchanger.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.mankevich.currencyexchanger.core.presentation.BaseViewModel
import by.mankevich.currencyexchanger.domain.entity.CurrencyRate
import by.mankevich.currencyexchanger.domain.repository.CurrencyExchangeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyExchangeViewModel @Inject constructor(
    private val repository: CurrencyExchangeRepository
) : BaseViewModel() {

    private val _currencyTypes = MutableLiveData<List<String>>()
    val currencyTypes: LiveData<List<String>>
        get() = _currencyTypes

    init {
        viewModelScope.launch {
            repository.fetchAndSaveCurrencyRates()
        }

        fetchCurrencyTypes()
    }

    private fun fetchCurrencyTypes() {
        viewModelScope.launch {
            val currencyTypesFlow = repository.getAllCurrencyTypes()
            currencyTypesFlow.collect{ currencyTypes->
                if (currencyTypes.isNotEmpty()) {
                    _currencyTypes.value = currencyTypes
                }
            }

        }
    }
}
