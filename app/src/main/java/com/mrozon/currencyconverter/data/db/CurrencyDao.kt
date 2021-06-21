package com.mrozon.currencyconverter.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(valutes: List<ValuteDb>)

    @Query("SELECT * FROM valutes")
    fun getPlants(): Flow<List<ValuteDb>>
}
