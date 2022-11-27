package by.mankevich.currencyexchanger.di

import android.content.Context
import androidx.room.Room
import by.mankevich.currencyexchanger.data.api.CurrencyExchangeApi
import by.mankevich.currencyexchanger.data.db.BalanceDao
import by.mankevich.currencyexchanger.data.db.CurrencyRateDao
import by.mankevich.currencyexchanger.data.db.CurrencyExchangeDatabase
import by.mankevich.currencyexchanger.data.repository.CurrencyExchangeRepositoryImpl
import by.mankevich.currencyexchanger.domain.repository.CurrencyExchangeRepository
import dagger.Module
import dagger.Provides

@Module
class CurrencyExchangeDataModule {

    @Provides
    fun provideCurrencyExchangeDatabase(@ApplicationContext context: Context): CurrencyExchangeDatabase{
        return Room.databaseBuilder(
            context,
            CurrencyExchangeDatabase::class.java,
            CurrencyExchangeDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideCurrencyExchangeRepository(
        currencyExchangeApi: CurrencyExchangeApi,
        balanceDao: BalanceDao,
        currencyRateDao: CurrencyRateDao,
    ): CurrencyExchangeRepository {
        return CurrencyExchangeRepositoryImpl(currencyExchangeApi, balanceDao, currencyRateDao)
    }

    @Provides
    fun provideBalanceDao(currencyExchangeDatabase: CurrencyExchangeDatabase) =
        currencyExchangeDatabase.getBalanceDao()

    @Provides
    fun provideCurrencyDao(currencyExchangeDatabase: CurrencyExchangeDatabase) =
        currencyExchangeDatabase.getCurrencyDao()
}
