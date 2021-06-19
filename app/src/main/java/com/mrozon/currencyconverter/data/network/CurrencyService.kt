package com.mrozon.currencyconverter.data.network

import com.mrozon.currencyconverter.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE

interface CurrencyService {
    @GET("archive/2021/06/18/daily_json.js")
    suspend fun getCurrencies(): CurrenciesResponse

    companion object {
        private const val BASE_URL = "https://www.cbr-xml-daily.ru/"

        fun create(): CurrencyService {
            val logger = HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CurrencyService::class.java)
        }
    }
}
