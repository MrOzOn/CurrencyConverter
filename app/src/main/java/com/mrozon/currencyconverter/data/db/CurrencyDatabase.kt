package com.mrozon.currencyconverter.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ValuteDb::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun plantDao(): CurrencyDao

    companion object {
        private const val DATABASE_NAME = "currencies.db"

        @Volatile private var instance: CurrencyDatabase? = null

        fun getInstance(context: Context): CurrencyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CurrencyDatabase {
            return Room.databaseBuilder(context, CurrencyDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
//                        override fun onOpen(db: SupportSQLiteDatabase) {
//                            super.onOpen(db)
//                        }
//
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
//                                .setInputData(workDataOf(KEY_FILENAME to PLANT_DATA_FILENAME))
//                                .build()
//                            WorkManager.getInstance(context).enqueue(request)
//                        }
                    }
                )
                .build()
        }
    }
}
