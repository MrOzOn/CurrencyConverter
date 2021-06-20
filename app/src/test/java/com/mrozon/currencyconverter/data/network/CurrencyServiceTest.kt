package com.mrozon.currencyconverter.data.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mrozon.currencyconverter.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@ExperimentalCoroutinesApi
class CurrencyServiceTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var service: CurrencyService

    @Before
    fun setUp(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(CurrencyService::class.java)
    }

    @Test
    fun `get currencies from real network request`()  = coroutinesTestRule.runBlockingTest {
        val response = service.getCurrencies()
        Assert.assertEquals(response.valute?.size ?: 0, 34)
    }
}
