package com.mrozon.currencyconverter.di

import com.mrozon.currencyconverter.data.network.CurrencyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideCurrencyService(): CurrencyService {
        return CurrencyService.create()
    }
}
