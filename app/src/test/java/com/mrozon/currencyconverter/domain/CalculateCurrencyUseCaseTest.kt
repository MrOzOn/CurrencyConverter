package com.mrozon.currencyconverter.domain

import com.mrozon.currencyconverter.CoroutineTestRule
import com.mrozon.currencyconverter.data.db.CurrencyDao
import com.mrozon.currencyconverter.data.db.ValuteDb
import com.mrozon.currencyconverter.data.network.Valute
import com.mrozon.currencyconverter.data.repository.UpdateValutesRepository
import com.mrozon.currencyconverter.domain.dto.CurrencyMapper
import com.mrozon.currencyconverter.domain.model.Currency
import com.mrozon.currencyconverter.domain.model.CurrentCurrencies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class CalculateCurrencyUseCaseTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var dao: CurrencyDao

    lateinit var useCase: CalculateCurrencyUseCase

    @Before
    fun setUp() {
        useCase = CalculateCurrencyUseCase(dao, CurrencyMapper())
    }

    @Test
    fun loadCurrencies_success() = coroutinesTestRule.runBlockingTest {
        val valute1 = ValuteDb(
            charCode = "HKD",
            value = 93.0223,
            id = "R01200",
            name = "Гонконгских долларов"
        )
        val valute2 = ValuteDb(
            charCode = "HKS",
            value = 93.0223,
            id = "R01201",
            name = "Гонконгских долларов"
        )
        val valute3 = ValuteDb(
            charCode = "HKY",
            value = 93.0223,
            id = "R01203",
            name = "Гонконгских долларов"
        )
        Mockito.`when`(dao.getValutes()).thenReturn(
            flowOf(listOf(valute1, valute2, valute3))
        )
        val expected = CurrentCurrencies(
            valutes= listOf(
                Currency(charCode="RUB", name="Российский рубль", value=1.0, selected=true),
                Currency(charCode="HKD", name="Гонконгских долларов", value=93.0223, selected=false),
                Currency(charCode="HKS", name="Гонконгских долларов", value=93.0223, selected=false),
                Currency(charCode="HKY", name="Гонконгских долларов", value=93.0223, selected=false)
            ),
            value=1.0)

        val actual = useCase.loadCurrencies()

        assertEquals(
            expected,
            actual.first()
        )
    }

    @Test
    fun loadCurrencies_load_empty_list() = coroutinesTestRule.runBlockingTest {
        Mockito.`when`(dao.getValutes()).thenReturn(
            flowOf(listOf())
        )
        val expected = CurrentCurrencies(
            valutes= listOf(
                Currency(charCode="RUB", name="Российский рубль", value=1.0, selected=true),
            ),
            value=1.0)

        val actual = useCase.loadCurrencies()

        assertEquals(
            expected,
            actual.first()
        )
    }

}
