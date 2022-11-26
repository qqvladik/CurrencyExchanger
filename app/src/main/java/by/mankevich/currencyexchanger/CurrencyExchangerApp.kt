package by.mankevich.currencyexchanger

import android.app.Application
import by.mankevich.currencyexchanger.di.CurrencyExchangeAppComponent
import by.mankevich.currencyexchanger.di.DaggerCurrencyExchangeAppComponent

class CurrencyExchangerApp : Application() {

    lateinit var appComponent: CurrencyExchangeAppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerCurrencyExchangeAppComponent.factory()
            .create(this)
    }
}
