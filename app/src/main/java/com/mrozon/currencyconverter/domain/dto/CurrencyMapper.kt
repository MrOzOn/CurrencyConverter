package com.mrozon.currencyconverter.domain.dto

import com.mrozon.currencyconverter.data.db.ValuteDb
import com.mrozon.currencyconverter.domain.model.Currency
import com.mrozon.currencyconverter.utils.BaseMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyMapper @Inject constructor(): BaseMapper<ValuteDb, Currency>() {

    override fun map(entity: ValuteDb) = Currency(
        charCode = entity.charCode,
        name = entity.name,
        value = entity.value,
        selected = false
    )
}
