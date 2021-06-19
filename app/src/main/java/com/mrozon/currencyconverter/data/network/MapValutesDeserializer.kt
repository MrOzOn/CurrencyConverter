package com.mrozon.currencyconverter.data.network

import com.google.gson.JsonDeserializer
import com.google.gson.JsonParseException
import com.google.gson.JsonElement
import com.google.gson.JsonDeserializationContext
import com.google.gson.Gson
import java.lang.reflect.Type

class MapValutesDeserializer : JsonDeserializer<Map<String, Valute>?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Map<String, Valute> {
        val result = mutableMapOf<String, Valute>()
        val valutes = json.asJsonObject.entrySet()
        valutes.forEach { (key, value) ->
            result[key] = Gson().fromJson(value, Valute::class.java)
        }
        return result.toMap()
    }
}
