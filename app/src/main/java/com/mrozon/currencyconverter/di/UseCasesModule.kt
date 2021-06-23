package com.mrozon.currencyconverter.di

import android.app.Application
import com.mrozon.currencyconverter.data.db.CurrencyDao
import com.mrozon.currencyconverter.data.network.CurrencyService
import com.mrozon.currencyconverter.data.repository.IUpdateValutesRepository
import com.mrozon.currencyconverter.data.repository.UpdateValutesRepository
import com.mrozon.currencyconverter.domain.CalculateCurrencyUseCase
import com.mrozon.currencyconverter.domain.ICalculateCurrencyUseCase
import com.mrozon.currencyconverter.domain.dto.CurrencyMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCasesModule {
    @Singleton
    @Provides
    fun provideCalculateCurrencyUseCase(
        currencyDao: CurrencyDao,
        dto: CurrencyMapper,
        application: Application
    ): ICalculateCurrencyUseCase = CalculateCurrencyUseCase(currencyDao, dto, application)
}
