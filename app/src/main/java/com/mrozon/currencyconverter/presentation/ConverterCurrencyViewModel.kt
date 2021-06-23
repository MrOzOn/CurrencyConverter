package com.mrozon.currencyconverter.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrozon.currencyconverter.CoroutineContextDispatchers
import com.mrozon.currencyconverter.R
import com.mrozon.currencyconverter.domain.ICalculateCurrencyUseCase
import com.mrozon.currencyconverter.domain.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
            val total = useCase.convertCurrency(selectedCurrency, currency, inputValue.toDouble())

            val currencyVH = CurrencyUI(
                charCode = currency.charCode,
                name = currency.name,
                total = if (currency.charCode == selected) inputValue else total.format(4),
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
        when (text) {
            delChar -> {
                val v: String = flowInputValue.value.format(4)
                if(v.length==1) {
                    flowInputValue.value = "0"
                } else {
                    flowInputValue.value = v.dropLast(1)
                }
            }
            comma -> {
                val v = flowInputValue.value
                if (!v.contains(comma))
                    flowInputValue.value = v+text
            }
            else -> {
                val v = flowInputValue.value
                flowInputValue.value = if (v=="0") text else v+text
            }
        }
    }
}

