package by.mankevich.currencyexchanger.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.mankevich.currencyexchanger.core.domain.IEntity

@Entity
data class Balance(
    @PrimaryKey val currencyType: String,
    val amount: Double
) : IEntity
