package com.mrozon.currencyconverter.di

import android.content.Context
import com.mrozon.currencyconverter.data.db.CurrencyDao
import com.mrozon.currencyconverter.data.db.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCurrencyDatabase(@ApplicationContext context: Context): CurrencyDatabase {
        return CurrencyDatabase.getInstance(context)
    }

    @Provides
    fun provideCurrencyDao(currencyDatabase: CurrencyDatabase): CurrencyDao {
        return currencyDatabase.plantDao()
    }

}
