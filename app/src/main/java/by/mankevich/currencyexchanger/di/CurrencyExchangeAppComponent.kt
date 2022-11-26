package by.mankevich.currencyexchanger.di

import android.content.Context
import by.mankevich.currencyexchanger.core.di.ViewModelFactoryModule
import by.mankevich.currencyexchanger.presentation.CurrencyExchangeActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ViewModelFactoryModule::class,
        CurrencyExchangeNetworkModule::class,
        CurrencyExchangeDataModule::class,
        CurrencyExchangeViewModelModule::class,
    ]
)
interface CurrencyExchangeAppComponent {

    fun injectActivity(activity: CurrencyExchangeActivity)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance @ApplicationContext context: Context): CurrencyExchangeAppComponent
    }
}
