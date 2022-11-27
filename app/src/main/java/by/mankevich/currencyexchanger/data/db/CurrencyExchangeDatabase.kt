package by.mankevich.currencyexchanger.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.mankevich.currencyexchanger.domain.entity.Balance
import by.mankevich.currencyexchanger.domain.entity.CurrencyRate

@Database(
    entities = [
        Balance::class,
        CurrencyRate::class
    ],
    version = CurrencyExchangeDatabase.DATABASE_VERSION
)
abstract class CurrencyExchangeDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "currency_exchange_database"
    }

    abstract fun getBalanceDao(): BalanceDao
    abstract fun getCurrencyDao(): CurrencyRateDao
}
