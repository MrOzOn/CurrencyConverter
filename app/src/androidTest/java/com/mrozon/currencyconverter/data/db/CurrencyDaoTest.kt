package com.mrozon.currencyconverter.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertEquals

@RunWith(AndroidJUnit4::class)
class CurrencyDaoTest {
    private lateinit var database: CurrencyDatabase
    private lateinit var dao: CurrencyDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val valute1 = ValuteDb("R01090B", "BYN", "Белорусский рубль", 28.7415f)
    private val valute2 = ValuteDb("R01100", "BGN", "Болгарский лев", 44.0295f)
    private val valute3 = ValuteDb("R01565", "PLN", "Польский злотый", 18.9354f)

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, CurrencyDatabase::class.java).build()
        dao = database.currencyDao()
        dao.insertAll(listOf(valute1, valute2, valute3))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun get_all_valutes_from_db() = runBlocking {
        val valutes = dao.getPlants().first()
        assertEquals(valutes.size,3)
    }

    @Test
    fun add_one_new_item() = runBlocking {
        val valute = ValuteDb("R01775", "CHF", "Швейцарский франк", 78.7071f)
        dao.insertAll(listOf(valute))
        val valutes = dao.getPlants().first()
        assertEquals(valutes.size,4)
    }

    @Test
    fun add_one_existing_item() = runBlocking {
        val valute = ValuteDb("R01100", "CHF", "Швейцарский франк", 78.7071f)
        dao.insertAll(listOf(valute))
        val valutes = dao.getPlants().first()
        assertEquals(valutes.size,3)
    }

}
