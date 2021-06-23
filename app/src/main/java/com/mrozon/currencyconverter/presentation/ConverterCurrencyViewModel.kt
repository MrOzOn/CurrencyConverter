package com.mrozon.currencyconverter.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrozon.currencyconverter.CoroutineContextDispatchers
import com.mrozon.currencyconverter.MyApp
import com.mrozon.currencyconverter.R
import com.mrozon.currencyconverter.domain.ICalculateCurrencyUseCase
import com.mrozon.currencyconverter.domain.model.Currency
import com.mrozon.currencyconverter.format
import com.mrozon.currencyconverter.presentation.model.CurrencyUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class ConverterCurrencyViewModel @Inject constructor(
    private val useCase: ICalculateCurrencyUseCase,
    private val dispatchers: CoroutineContextDispatchers,
    application: Application
): ViewModel() {

    private val rubCharCode = application.getString(R.string.rub_char_code)
    private val delChar = application.getString(R.string.buttonDel)
    private val comma = application.getString(R.string.buttonComma)

    private val flowCurrencies = useCase.loadCurrencies().stateIn(
        viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList())
    private val flowSelected = MutableStateFlow(rubCharCode)
    private val flowInputValue = MutableStateFlow("1")

    val state: Flow<List<CurrencyUI>> = combine(
        flowCurrencies,
        flowSelected,
        flowInputValue
    ) { currencies, selected, inputValue ->
        calculateCorrectExchange(currencies, selected, inputValue)
    }.stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = emptyList())

    private fun calculateCorrectExchange(
        currencies: List<Currency>,
        selected: String,
        inputValue: String
    ): List<CurrencyUI> {
        val selectedCurrency = currencies.firstOrNull { it.charCode == selected } ?: return emptyList()

        val listCurrencyVH = mutableListOf<CurrencyUI>()
        currencies.forEach { currency ->
            val total = useCase.convertCurrency(selectedCurrency, currency,
                inputValue.replace(',','.').toDouble())

            val currencyVH = CurrencyUI(
                charCode = currency.charCode,
                name = currency.name,
                total = if (currency.charCode == selected) inputValue else total.format(MyApp.PRECISION),
                selected = currency.charCode == selected
            )
            listCurrencyVH.add(currencyVH)
        }
        return listCurrencyVH.toList()
    }

    fun selectCurrency(charCode: String) {
        flowSelected.value = charCode
        flowInputValue.value = "1"
    }

    fun input(text: String) {
        val inputValue = flowInputValue.value
        when (text) {
            delChar -> {
                flowInputValue.value = if (inputValue.length==1) "0" else inputValue.dropLast(1)
            }
            comma -> {
                if (!inputValue.contains(comma))
                    flowInputValue.value = inputValue+text
            }
            else -> {
                flowInputValue.value = if (inputValue=="0") text else inputValue+text
            }
        }
    }
}

