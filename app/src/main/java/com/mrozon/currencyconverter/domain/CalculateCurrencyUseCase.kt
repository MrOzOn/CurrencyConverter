package com.mrozon.currencyconverter.domain

import android.app.Application
import android.content.res.Resources
import com.mrozon.currencyconverter.R
import com.mrozon.currencyconverter.data.db.CurrencyDao
import com.mrozon.currencyconverter.domain.dto.CurrencyMapper
import com.mrozon.currencyconverter.domain.model.Currency
import com.mrozon.currencyconverter.domain.model.CurrentCurrencies
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CalculateCurrencyUseCase @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val dto: CurrencyMapper,
    private val application: Application
): ICalculateCurrencyUseCase {
    override fun loadCurrencies(): Flow<List<Currency>> = flow{
        val rubCharCode = application.getString(R.string.rub_char_code)
        val rubName = application.getString(R.string.rub_name)
        currencyDao.getValutes().collect { listValuteDb ->
            val currencies = mutableListOf<Currency>()
            val rub = Currency(rubCharCode, rubName, 1.0)
            currencies.add(rub)
            currencies.addAll(dto.map(listValuteDb))
            emit(currencies.toList())
        }
    }

    override fun convertCurrency(
        from: Currency,
        to: Currency,
        value: Double
    ) =  from.value * value / to.value



}
