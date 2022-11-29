package by.mankevich.currencyexchanger.di

import android.content.Context
import androidx.room.Room
import by.mankevich.currencyexchanger.data.repository.ConnectivityChecker
import by.mankevich.currencyexchanger.data.api.CurrencyExchangeApi
import by.mankevich.currencyexchanger.data.db.BalanceDao
import by.mankevich.currencyexchanger.data.db.CurrencyRateDao
import by.mankevich.currencyexchanger.data.db.CurrencyExchangeDatabase
import by.mankevich.currencyexchanger.data.db.UserDao
import by.mankevich.currencyexchanger.data.repository.CommissionCalculatorImpl
import by.mankevich.currencyexchanger.data.repository.CurrencyExchangeRepositoryImpl
import by.mankevich.currencyexchanger.domain.repository.CommissionCalculator
import by.mankevich.currencyexchanger.domain.repository.CurrencyExchangeRepository
import dagger.Module
import dagger.Provides

@Module
class CurrencyExchangeDataModule {

    @Provides
    fun provideCurrencyExchangeDatabase(@ApplicationContext context: Context): CurrencyExchangeDatabase {
        return Room.databaseBuilder(
            context,
            CurrencyExchangeDatabase::class.java,
            CurrencyExchangeDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideCurrencyExchangeRepository(
        currencyExchangeApi: CurrencyExchangeApi,
        connectivityChecker: ConnectivityChecker,
        balanceDao: BalanceDao,
        currencyRateDao: CurrencyRateDao,
        userDao: UserDao,
        commissionCalculator: CommissionCalculator
    ): CurrencyExchangeRepository {
        return CurrencyExchangeRepositoryImpl(
            currencyExchangeApi,
            connectivityChecker,
            balanceDao,
            currencyRateDao,
            userDao,
            commissionCalculator
        )
    }

    @Provides
    fun provideConnectivityChecker(@ApplicationContext context: Context): ConnectivityChecker {
        return ConnectivityChecker(context)
    }

    @Provides
    fun provideCommissionCalculator(
        userDao: UserDao
    ): CommissionCalculator {
        return CommissionCalculatorImpl(userDao)
    }

    @Provides
    fun provideBalanceDao(currencyExchangeDatabase: CurrencyExchangeDatabase) =
        currencyExchangeDatabase.getBalanceDao()

    @Provides
    fun provideCurrencyDao(currencyExchangeDatabase: CurrencyExchangeDatabase) =
        currencyExchangeDatabase.getCurrencyDao()

    @Provides
    fun provideUserDao(currencyExchangeDatabase: CurrencyExchangeDatabase) =
        currencyExchangeDatabase.getUserDao()
}
