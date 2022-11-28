package by.mankevich.currencyexchanger.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.mankevich.currencyexchanger.domain.entity.Money
import kotlinx.coroutines.flow.Flow

@Dao
interface BalanceDao {

    @Query("SELECT (SELECT COUNT(*) FROM Money) == 0")
    suspend fun isEmpty(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalance(balance: Money)

    @Query("SELECT * FROM Money ORDER BY amount DESC")
    fun getAllBalances(): Flow<List<Money>>

    @Query("SELECT * FROM Money WHERE currencyType = :currencyType ")
    suspend fun getBalance(currencyType: String): Money?
}
