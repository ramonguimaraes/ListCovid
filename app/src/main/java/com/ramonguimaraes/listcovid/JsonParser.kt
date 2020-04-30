package com.ramonguimaraes.listcovid

import android.content.Context
import android.util.Log
import org.json.JSONArray
import java.io.IOException
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class JsonParser {

    fun readJson(context: Context, lista: ArrayList<Boletim>): ArrayList<Boletim>{
        val json: String?

        try {

            val inputStream = context.assets.open("data.json")
            json = inputStream.bufferedReader().use {
                it.readText()
            }

            val jsonArray = JSONArray(json)

            for (i in 0 until jsonArray.length()-1){

                val js = jsonArray.getJSONObject(i)
                val suspeitos = js.getInt("Suspeitos")
                val confirmados = js.getInt("Confirmados")
                val descartados = js.getInt("Descartados")
                val monitoramento = js.getInt("Monitoramento")
                val curados = js.getInt("Curados")
                val sDomiciliar = js.getInt("Sdomiciliar")
                val sHospitalar = js.getInt("Shospitalar")
                val mortes = js.getInt("mortes")
                val data = dataParse(js.getString("boletim").substring(0,10))
                val hora = js.getString("boletim").substring(11,16).replace(":", "h")

                val boletim = Boletim(suspeitos, confirmados, descartados, monitoramento, curados, sDomiciliar, sHospitalar, mortes, data, hora)
                lista.add(boletim)
            }
        } catch (e : IOException){
            Log.e("Erro", "Impossivel ler JSON")
        }

        return lista
    }

    private fun dataParse(date: String): String {

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formatedDate = LocalDate.parse(date)
        return formatedDate.format(formatter)

    }
}