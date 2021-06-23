package com.mrozon.currencyconverter.presentation

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mrozon.currencyconverter.MyApp
import com.mrozon.currencyconverter.presentation.model.CurrencyUI

@BindingAdapter("show_total")
fun TextView.showTotal(currencyUI: CurrencyUI) {
    if(currencyUI.selected) {
        setTextColor(Color.MAGENTA)
        text = currencyUI.total
    } else {
        setTextColor(Color.BLACK)
        text = currencyUI.total.format(MyApp.PRECISION)
    }
}
