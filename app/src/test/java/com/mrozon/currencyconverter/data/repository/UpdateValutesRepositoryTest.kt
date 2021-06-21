package com.mrozon.currencyconverter.data.repository

import com.mrozon.currencyconverter.CoroutineTestRule
import com.mrozon.currencyconverter.data.db.CurrencyDao
import com.mrozon.currencyconverter.data.network.CurrenciesResponse
import com.mrozon.currencyconverter.data.network.CurrencyService
import com.mrozon.currencyconverter.data.network.Valute
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
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
class UpdateValutesRepositoryTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var service: CurrencyService

    @Mock
    lateinit var dao: CurrencyDao

    lateinit var repository: UpdateValutesRepository

    @Before
    fun setUp() {
        repository = UpdateValutesRepository(service, dao)
    }

    @Test
    fun update_success() = coroutinesTestRule.runBlockingTest {
        val valute1 = Valute(
            charCode = "HKD",
            value = 93.0223,
            previous = 93.3763,
            iD = "R01200",
            nominal = 10,
            numCode = "344",
            name = "Гонконгских долларов"
        )
        val valute2 = Valute(
            charCode = "HKS",
            value = 93.0223,
            previous = 93.3763,
            iD = "R01201",
            nominal = 10,
            numCode = "344",
            name = "Гонконгских долларов"
        )
        val valute3 = Valute(
            charCode = "HKY",
            value = 93.0223,
            previous = 93.3763,
            iD = "R01203",
            nominal = 10,
            numCode = "344",
            name = "Гонконгских долларов"
        )
        val response = CurrenciesResponse (
            previousURL = "",
            timestamp = "",
            date = "",
            previousDate = "",
            valute = mapOf("HKD" to valute1, "HKS" to valute2, "HKY" to valute3)
        )

        Mockito.`when`(service.getCurrencies()).thenReturn(
            response
        )
        repository.update()
//        assertEquals(1,1)
    }

}
