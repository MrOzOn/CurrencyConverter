package com.mrozon.currencyconverter.domain

import com.mrozon.currencyconverter.domain.model.CurrentCurrencies
import kotlinx.coroutines.flow.Flow

interface ICalculateCurrencyUseCase {
    fun loadCurrencies(): Flow<CurrentCurrencies>
}
