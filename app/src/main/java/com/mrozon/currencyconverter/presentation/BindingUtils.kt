package com.mrozon.currencyconverter.presentation

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("show_total")
fun TextView.showTotal(currencyUI: CurrencyUI) {
//    text = currencyUI.total.format(4)
    if(currencyUI.selected) {
        setTextColor(Color.MAGENTA)
        text = currencyUI.total.format(4)
    } else {
        setTextColor(Color.BLACK)
        text = currencyUI.total.format(4)
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this).trimEnd('0')//.trim('.')