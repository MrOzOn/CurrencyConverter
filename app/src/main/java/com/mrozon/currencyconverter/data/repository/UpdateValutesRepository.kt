package com.mrozon.currencyconverter.data.repository

import com.mrozon.currencyconverter.data.db.CurrencyDao
import com.mrozon.currencyconverter.data.db.ValuteDb
import com.mrozon.currencyconverter.data.network.CurrencyService
import com.mrozon.currencyconverter.data.network.toValuteDb
import timber.log.Timber
import javax.inject.Inject

class UpdateValutesRepository @Inject constructor(
    private val currencyService: CurrencyService,
    private val currencyDao: CurrencyDao
): IUpdateValutesRepository {

    override suspend fun update() {
        val currencies = currencyService.getCurrencies()
        val valutes = mutableListOf<ValuteDb>()
        currencies.valute?.entries?.forEach { (_, value) ->
            valutes.add(value.toValuteDb())
        }
        currencyDao.insertAll(valutes)
        Timber.d("currencies updated!!!")
    }
}
