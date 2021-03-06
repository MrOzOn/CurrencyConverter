package com.mrozon.currencyconverter.domain

import androidx.test.core.app.ApplicationProvider
import com.mrozon.currencyconverter.CoroutineTestRule
import com.mrozon.currencyconverter.data.db.CurrencyDao
import com.mrozon.currencyconverter.data.db.ValuteDb
import com.mrozon.currencyconverter.domain.dto.CurrencyMapper
import com.mrozon.currencyconverter.domain.model.Currency
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [29])
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
        useCase = CalculateCurrencyUseCase(dao, CurrencyMapper(), ApplicationProvider.getApplicationContext())
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
        val expected = listOf(
                Currency(charCode="RUB", name="Российский рубль", value=1.0),
                Currency(charCode="HKD", name="Гонконгских долларов", value=93.0223),
                Currency(charCode="HKS", name="Гонконгских долларов", value=93.0223),
                Currency(charCode="HKY", name="Гонконгских долларов", value=93.0223)
            )

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
        val expected = listOf(
                Currency(charCode="RUB", name="Российский рубль", value=1.0),
            )

        val actual = useCase.loadCurrencies()

        assertEquals(
            expected,
            actual.first()
        )
    }

    @Test
    fun convertCurrency_change_rub2eur()  {
        val from = Currency(charCode = "RUB", name = "Российский рубль", value = 1.0)
        val to = Currency(charCode = "EUR", name = "Евро", value = 85.9943)

        val expected = 0.01162

        val actual = useCase.convertCurrency(from, to, 1.0)
        assertEquals(
            expected,
            actual,
            0.0001
        )
    }

    @Test
    fun convertCurrency_change_eur2rub()  {
        val from = Currency(charCode = "EUR", name = "Евро", value = 85.9943)
        val to = Currency(charCode = "RUB", name = "Российский рубль", value = 1.0)

        val expected = 429.9715

        val actual = useCase.convertCurrency(from, to, 5.0)
        assertEquals(
            expected,
            actual,
            0.0001
        )
    }

    @Test
    fun convertCurrency_change_eur2usd()  {
        val from = Currency(charCode = "EUR", name = "Евро", value = 85.9943)
        val to = Currency(charCode = "USD", name = "Доллар США", value = 72.5048)

        val expected = 5.9302

        val actual = useCase.convertCurrency(from, to, 5.0)
        assertEquals(
            expected,
            actual,
            0.0001
        )
    }

}
