package com.mrozon.currencyconverter.domain.model

data class Currency(
    val charCode: String,
    val name: String,
    val value: Double,
    val selected: Boolean
)
