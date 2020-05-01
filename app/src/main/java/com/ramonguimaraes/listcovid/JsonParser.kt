package com.ramonguimaraes.listcovid
import android.util.Log
import org.json.JSONArray
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object JsonParser {

    fun readBoletins(json: JSONArray): ArrayList<Boletim>?{
        val boletins = arrayListOf<Boletim>()
        try {

            for (i in 0 until  json.length() -1){
                val js = json.getJSONObject(i)
                val dia = dateParse(js.getString("boletim").substring(0,10))
                val hora = js.getString("boletim").substring(11,18)
                val boletim = Boletim(js.getInt("Suspeitos"),
                    js.getInt("Confirmados"),
                    js.getInt("Descartados"),
                    js.getInt("Monitoramento"),
                    js.getInt("Descartados"),
                    js.getInt("Sdomiciliar"),
                    js.getInt("Shopitalar"),
                    js.getInt("mortes"),dia,hora)

                boletins.add(boletim)
            }
        } catch (e : IOException){
            Log.e("Erro", "Impossivel ler JSON")
        }

        return boletins
    }

    private fun dateParse(date: String): String {

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formatedDate = LocalDate.parse(date)
        return formatedDate.format(formatter)

    }
}