package com.mrozon.currencyconverter.data.network

import com.google.gson.annotations.SerializedName
import com.mrozon.currencyconverter.data.db.ValuteDb

data class CurrenciesResponse(

	@field:SerializedName("PreviousURL")
	val previousURL: String? = null,

	@field:SerializedName("Timestamp")
	val timestamp: String? = null,

	@field:SerializedName("Date")
	val date: String? = null,

	@field:SerializedName("PreviousDate")
	val previousDate: String? = null,

	@field:SerializedName("Valute")
	val valute: Map<String, Valute>? = null
)

data class Valute(

	@field:SerializedName("CharCode")
	val charCode: String? = null,

	@field:SerializedName("Value")
	val value: Double? = null,

	@field:SerializedName("Previous")
	val previous: Double? = null,

	@field:SerializedName("ID")
	val iD: String? = null,

	@field:SerializedName("Nominal")
	val nominal: Int? = null,

	@field:SerializedName("NumCode")
	val numCode: String? = null,

	@field:SerializedName("Name")
	val name: String? = null
)

fun Valute.toValuteDb() = ValuteDb(
	id = this.iD?:"",
	charCode = this.charCode?:"",
	name = this.name?:"",
	value = (this.value?:0.0) / (this.nominal?:1)
)

