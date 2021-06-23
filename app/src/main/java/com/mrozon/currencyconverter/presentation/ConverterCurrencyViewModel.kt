package com.mrozon.currencyconverter.presentation

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrozon.currencyconverter.CoroutineContextDispatchers
import com.mrozon.currencyconverter.R
import com.mrozon.currencyconverter.data.repository.IUpdateValutesRepository
import com.mrozon.currencyconverter.domain.ICalculateCurrencyUseCase
import com.mrozon.currencyconverter.domain.model.Currency
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

    private val flowCurrencies = useCase.loadCurrencies().stateIn(
        viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList())
    private val flowSelected = MutableStateFlow(rubCharCode)
    private val flowInputValue = MutableStateFlow(1.0)

    val state: Flow<List<CurrencyVH>> = combine(
        flowCurrencies,
        flowSelected,
        flowInputValue
    ) { currencies, selected, inputValue ->
        calculateCorrectExchange(currencies, selected, inputValue)
    }.stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = emptyList())

    private fun calculateCorrectExchange(
        currencies: List<Currency>,
        selected: String,
        inputValue: Double
    ): List<CurrencyVH> {
        val selectedCurrency = currencies.firstOrNull { it.charCode == selected } ?: return emptyList()
        val rubCurrency = currencies.firstOrNull { it.charCode == rubCharCode } ?: return emptyList()

        val listCurrencyVH = mutableListOf<CurrencyVH>()
        currencies.forEach { currency ->
            val total = useCase.convertCurrency(currency, selectedCurrency, rubCurrency, inputValue)
            val currencyVH = CurrencyVH(
                charCode = currency.charCode,
                name = currency.name,
                total = total,
                selected = currency.charCode == selected
            )
            listCurrencyVH.add(currencyVH)
        }
        return listCurrencyVH.toList()
    }

    data class CurrencyVH (
        val charCode: String,
        val name: String,
        val total: Double,
        val selected: Boolean,
        )


}

