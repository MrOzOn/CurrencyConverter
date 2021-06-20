package com.mrozon.currencyconverter.data.network

import com.google.gson.GsonBuilder
import org.junit.Assert.*
import org.junit.Test

class MapValutesDeserializerTest {
    @Test
    fun check_correct_json_to_POJO() {
        val json = """
            {
                "Date": "2021-06-19T11:30:00+03:00",
                "PreviousDate": "2021-06-18T11:30:00+03:00",
                "PreviousURL": "\/\/www.cbr-xml-daily.ru\/archive\/2021\/06\/18\/daily_json.js",
                "Timestamp": "2021-06-19T22:00:00+03:00",
                "Valute": {
                    "AUD": {
                        "ID": "R01010",
                        "NumCode": "036",
                        "CharCode": "AUD",
                        "Nominal": 1,
                        "Name": "Австралийский доллар",
                        "Value": 54.5056,
                        "Previous": 55.1834
                    },
                    "AZN": {
                        "ID": "R01020A",
                        "NumCode": "944",
                        "CharCode": "AZN",
                        "Nominal": 1,
                        "Name": "Азербайджанский манат",
                        "Value": 42.5083,
                        "Previous": 42.675
                    },
                    "GBP": {
                        "ID": "R01035",
                        "NumCode": "826",
                        "CharCode": "GBP",
                        "Nominal": 1,
                        "Name": "Фунт стерлингов Соединенного королевства",
                        "Value": 100.3808,
                        "Previous": 101.427
                    },
                    "AMD": {
                        "ID": "R01060",
                        "NumCode": "051",
                        "CharCode": "AMD",
                        "Nominal": 100,
                        "Name": "Армянских драмов",
                        "Value": 13.941,
                        "Previous": 13.9903
                    },
                    "BYN": {
                        "ID": "R01090B",
                        "NumCode": "933",
                        "CharCode": "BYN",
                        "Nominal": 1,
                        "Name": "Белорусский рубль",
                        "Value": 28.7415,
                        "Previous": 28.7729
                    },
                    "BGN": {
                        "ID": "R01100",
                        "NumCode": "975",
                        "CharCode": "BGN",
                        "Nominal": 1,
                        "Name": "Болгарский лев",
                        "Value": 44.0295,
                        "Previous": 44.321
                    },
                    "BRL": {
                        "ID": "R01115",
                        "NumCode": "986",
                        "CharCode": "BRL",
                        "Nominal": 1,
                        "Name": "Бразильский реал",
                        "Value": 14.4198,
                        "Previous": 14.3415
                    },
                    "HUF": {
                        "ID": "R01135",
                        "NumCode": "348",
                        "CharCode": "HUF",
                        "Nominal": 100,
                        "Name": "Венгерских форинтов",
                        "Value": 24.2774,
                        "Previous": 24.5218
                    },
                    "HKD": {
                        "ID": "R01200",
                        "NumCode": "344",
                        "CharCode": "HKD",
                        "Nominal": 10,
                        "Name": "Гонконгских долларов",
                        "Value": 93.0223,
                        "Previous": 93.3763
                    },
                    "DKK": {
                        "ID": "R01215",
                        "NumCode": "208",
                        "CharCode": "DKK",
                        "Nominal": 1,
                        "Name": "Датская крона",
                        "Value": 11.5799,
                        "Previous": 11.6565
                    },
                    "USD": {
                        "ID": "R01235",
                        "NumCode": "840",
                        "CharCode": "USD",
                        "Nominal": 1,
                        "Name": "Доллар США",
                        "Value": 72.2216,
                        "Previous": 72.5048
                    },
                    "EUR": {
                        "ID": "R01239",
                        "NumCode": "978",
                        "CharCode": "EUR",
                        "Nominal": 1,
                        "Name": "Евро",
                        "Value": 85.9943,
                        "Previous": 86.7012
                    },
                    "INR": {
                        "ID": "R01270",
                        "NumCode": "356",
                        "CharCode": "INR",
                        "Nominal": 100,
                        "Name": "Индийских рупий",
                        "Value": 97.7354,
                        "Previous": 98.2377
                    },
                    "KZT": {
                        "ID": "R01335",
                        "NumCode": "398",
                        "CharCode": "KZT",
                        "Nominal": 100,
                        "Name": "Казахстанских тенге",
                        "Value": 16.8527,
                        "Previous": 16.9505
                    },
                    "CAD": {
                        "ID": "R01350",
                        "NumCode": "124",
                        "CharCode": "CAD",
                        "Nominal": 1,
                        "Name": "Канадский доллар",
                        "Value": 58.4412,
                        "Previous": 58.9326
                    },
                    "KGS": {
                        "ID": "R01370",
                        "NumCode": "417",
                        "CharCode": "KGS",
                        "Nominal": 100,
                        "Name": "Киргизских сомов",
                        "Value": 85.3006,
                        "Previous": 85.7008
                    },
                    "CNY": {
                        "ID": "R01375",
                        "NumCode": "156",
                        "CharCode": "CNY",
                        "Nominal": 1,
                        "Name": "Китайский юань",
                        "Value": 11.2126,
                        "Previous": 11.279
                    },
                    "MDL": {
                        "ID": "R01500",
                        "NumCode": "498",
                        "CharCode": "MDL",
                        "Nominal": 10,
                        "Name": "Молдавских леев",
                        "Value": 40.4037,
                        "Previous": 40.733
                    },
                    "NOK": {
                        "ID": "R01535",
                        "NumCode": "578",
                        "CharCode": "NOK",
                        "Nominal": 10,
                        "Name": "Норвежских крон",
                        "Value": 84.4282,
                        "Previous": 85.3691
                    },
                    "PLN": {
                        "ID": "R01565",
                        "NumCode": "985",
                        "CharCode": "PLN",
                        "Nominal": 1,
                        "Name": "Польский злотый",
                        "Value": 18.9354,
                        "Previous": 19.1265
                    },
                    "RON": {
                        "ID": "R01585F",
                        "NumCode": "946",
                        "CharCode": "RON",
                        "Nominal": 1,
                        "Name": "Румынский лей",
                        "Value": 17.4803,
                        "Previous": 17.5987
                    },
                    "XDR": {
                        "ID": "R01589",
                        "NumCode": "960",
                        "CharCode": "XDR",
                        "Nominal": 1,
                        "Name": "СДР (специальные права заимствования)",
                        "Value": 103.2271,
                        "Previous": 104.3844
                    },
                    "SGD": {
                        "ID": "R01625",
                        "NumCode": "702",
                        "CharCode": "SGD",
                        "Nominal": 1,
                        "Name": "Сингапурский доллар",
                        "Value": 53.8766,
                        "Previous": 54.1566
                    },
                    "TJS": {
                        "ID": "R01670",
                        "NumCode": "972",
                        "CharCode": "TJS",
                        "Nominal": 10,
                        "Name": "Таджикских сомони",
                        "Value": 63.3245,
                        "Previous": 63.5728
                    },
                    "TRY": {
                        "ID": "R01700J",
                        "NumCode": "949",
                        "CharCode": "TRY",
                        "Nominal": 10,
                        "Name": "Турецких лир",
                        "Value": 83.2871,
                        "Previous": 84.1025
                    },
                    "TMT": {
                        "ID": "R01710A",
                        "NumCode": "934",
                        "CharCode": "TMT",
                        "Nominal": 1,
                        "Name": "Новый туркменский манат",
                        "Value": 20.6643,
                        "Previous": 20.7453
                    },
                    "UZS": {
                        "ID": "R01717",
                        "NumCode": "860",
                        "CharCode": "UZS",
                        "Nominal": 10000,
                        "Name": "Узбекских сумов",
                        "Value": 68.2557,
                        "Previous": 68.2975
                    },
                    "UAH": {
                        "ID": "R01720",
                        "NumCode": "980",
                        "CharCode": "UAH",
                        "Nominal": 10,
                        "Name": "Украинских гривен",
                        "Value": 26.5806,
                        "Previous": 26.6856
                    },
                    "CZK": {
                        "ID": "R01760",
                        "NumCode": "203",
                        "CharCode": "CZK",
                        "Nominal": 10,
                        "Name": "Чешских крон",
                        "Value": 33.7642,
                        "Previous": 34.0366
                    },
                    "SEK": {
                        "ID": "R01770",
                        "NumCode": "752",
                        "CharCode": "SEK",
                        "Nominal": 10,
                        "Name": "Шведских крон",
                        "Value": 84.3878,
                        "Previous": 85.2045
                    },
                    "CHF": {
                        "ID": "R01775",
                        "NumCode": "756",
                        "CharCode": "CHF",
                        "Nominal": 1,
                        "Name": "Швейцарский франк",
                        "Value": 78.7071,
                        "Previous": 79.4486
                    },
                    "ZAR": {
                        "ID": "R01810",
                        "NumCode": "710",
                        "CharCode": "ZAR",
                        "Nominal": 10,
                        "Name": "Южноафриканских рэндов",
                        "Value": 51.3273,
                        "Previous": 51.7097
                    },
                    "KRW": {
                        "ID": "R01815",
                        "NumCode": "410",
                        "CharCode": "KRW",
                        "Nominal": 1000,
                        "Name": "Вон Республики Корея",
                        "Value": 63.9421,
                        "Previous": 64.1329
                    },
                    "JPY": {
                        "ID": "R01820",
                        "NumCode": "392",
                        "CharCode": "JPY",
                        "Nominal": 100,
                        "Name": "Японских иен",
                        "Value": 65.6292,
                        "Previous": 65.55
                    }
                }
            }
        """.trimIndent()
        val gson = GsonBuilder()
            .registerTypeAdapter(Map::class.java, MapValutesDeserializer())
            .create()
        val response = gson.fromJson(json, CurrenciesResponse::class.java)
        assertEquals(response.valute?.size?:0, 34)
    }
}
