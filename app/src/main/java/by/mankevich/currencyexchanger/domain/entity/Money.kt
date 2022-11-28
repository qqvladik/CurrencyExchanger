package by.mankevich.currencyexchanger.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.mankevich.currencyexchanger.core.domain.IEntity

@Entity
data class Money(
    @PrimaryKey var currencyType: String,
    var amount: Double
) : IEntity
