package by.mankevich.currencyexchanger.di

import androidx.lifecycle.ViewModel
import by.mankevich.currencyexchanger.core.di.ViewModelKey
import by.mankevich.currencyexchanger.presentation.CurrencyExchangeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CurrencyExchangeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyExchangeViewModel::class)
    fun provideCurrencyExchangeViewModel(currencyExchangeViewModel: CurrencyExchangeViewModel): ViewModel
}
