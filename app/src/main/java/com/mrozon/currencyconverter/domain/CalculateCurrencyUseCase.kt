package com.mrozon.currencyconverter.domain

import com.mrozon.currencyconverter.data.db.CurrencyDao
import com.mrozon.currencyconverter.domain.dto.CurrencyMapper
import com.mrozon.currencyconverter.domain.model.Currency
import com.mrozon.currencyconverter.domain.model.CurrentCurrencies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CalculateCurrencyUseCase @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val dto: CurrencyMapper
): ICalculateCurrencyUseCase {
    override fun loadCurrencies(): Flow<CurrentCurrencies> = flow{
        val currencies = mutableListOf<Currency>()
        val rub = Currency("RUB", "Российский рубль", 1.0, true)
        currencies.add(rub)
        currencyDao.getValutes().collect { listValuteDb ->
            currencies.addAll(dto.map(listValuteDb))
        }
        emit(CurrentCurrencies(currencies, 1.0))
    }
}
