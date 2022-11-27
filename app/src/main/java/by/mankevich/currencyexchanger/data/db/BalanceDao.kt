package by.mankevich.currencyexchanger.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.mankevich.currencyexchanger.domain.entity.Balance
import kotlinx.coroutines.flow.Flow

@Dao
interface BalanceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBalance(balance: Balance)

    @Query("SELECT * FROM Balance ORDER BY amount DESC")
    fun getAllBalances(): Flow<List<Balance>>

    @Query("SELECT * FROM Balance WHERE currencyType = :currencyType ")
    fun getBalance(currencyType: String): Balance?
}
