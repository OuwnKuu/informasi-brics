 package com.example.informasibrics

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object ApiClient {
    fun getNegara(): List<Negara> {
        val url = URL("https://countriesnow.space/api/v0.1/countries/flag/images")
        val connection = url.openConnection() as HttpsURLConnection
        connection.requestMethod = "GET"

        val reader = BufferedReader(InputStreamReader(connection.inputStream))
        val response = StringBuilder()
        var line: String?

        while (true) {
            line = reader.readLine()
            if (line == null) break; response.append(line)
        }
        reader.close()
        connection.disconnect()

        val jsonObject = JSONObject(response.toString())
        val arrayNegara = jsonObject.getJSONArray("data")
        val daftarNegara = mutableListOf<Negara>()

        for (i in 0 until arrayNegara.length()) {
            val objectNegara = arrayNegara.getJSONObject(i)
            val negara = Negara(
                benderaNegara = objectNegara.getString("flag"),
                namaNegara = objectNegara.getString("name"),
                iso2 = objectNegara.getString("iso2"),
                iso3 = objectNegara.getString("iso3")
            )
            daftarNegara.add(negara)

        }
        return daftarNegara
    }
}