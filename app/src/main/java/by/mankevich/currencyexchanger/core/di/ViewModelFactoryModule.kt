package by.mankevich.currencyexchanger.core.di

import androidx.lifecycle.ViewModelProvider
import by.mankevich.currencyexchanger.core.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
