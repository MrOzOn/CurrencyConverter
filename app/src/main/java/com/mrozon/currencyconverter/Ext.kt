package com.mrozon.currencyconverter

import java.text.DecimalFormatSymbols

fun Double.format(digits: Int) = "%.${digits}f".format(this)
    .trimEnd('0').
    trimEnd(DecimalFormatSymbols.getInstance().decimalSeparator)
