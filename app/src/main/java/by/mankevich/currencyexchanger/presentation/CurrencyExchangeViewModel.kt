package by.mankevich.currencyexchanger.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.mankevich.currencyexchanger.core.presentation.BaseViewModel
import by.mankevich.currencyexchanger.data.api.response.CurrencyExchangeRatesResponse
import by.mankevich.currencyexchanger.domain.repository.CurrencyExchangeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyExchangeViewModel @Inject constructor(
    private val repository: CurrencyExchangeRepository
) : BaseViewModel() {

    private val _response = MutableLiveData<CurrencyExchangeRatesResponse>()
    val response: LiveData<CurrencyExchangeRatesResponse> = _response

    init {
        viewModelScope.launch {
            _response.value = repository.fetchRates()
        }
    }
}
