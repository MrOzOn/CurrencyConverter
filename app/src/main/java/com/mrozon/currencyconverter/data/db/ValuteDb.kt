package com.mrozon.currencyconverter.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "valutes")
data class ValuteDb(
    @PrimaryKey
    val id: String,
    val charCode: String,
    val name: String,
    val value: Float
)
