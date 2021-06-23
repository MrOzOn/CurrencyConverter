package com.mrozon.currencyconverter.di

import com.mrozon.currencyconverter.data.db.CurrencyDao
import com.mrozon.currencyconverter.data.network.CurrencyService
import com.mrozon.currencyconverter.data.repository.IUpdateValutesRepository
import com.mrozon.currencyconverter.data.repository.UpdateValutesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ProvidersModule {
    @Singleton
    @Provides
    fun provideUpdateValutesRepository(currencyService: CurrencyService, currencyDao: CurrencyDao) =
        UpdateValutesRepository(currencyService, currencyDao)
}
