package by.mankevich.currencyexchanger.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val name: String,
    var counterFreeCommission: Int = 0
)
