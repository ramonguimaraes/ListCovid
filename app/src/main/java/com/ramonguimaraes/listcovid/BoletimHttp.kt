package com.ramonguimaraes.listcovid

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

object BoletimHttp {

    val url = "https://raw.githubusercontent.com/ramonsl/ws-covid/master/db.json"

    fun readBoletins(json: JSONArray): ArrayList<Boletim>?{
        val boletins = arrayListOf<Boletim>()
        try {
            for (i in 0 until  json.length() -1){
                val js = json.getJSONObject(i)
                val dia = formatarData(js.getString("boletim").substring(0,10))
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
        }
        catch (e : IOException){
            Log.e("Erro", "Impossivel ler JSON")
        }
        return boletins
    }

    fun formatarData(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val data = LocalDate.parse(date)
        return data.format(formatter)
    }

    fun loadBoletim(): ArrayList<Boletim>?{
        val client = OkHttpClient.Builder()
            .readTimeout(5,TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        val jsonString = response.body?.string()
        val json = JSONArray(jsonString)

        println(json)

        return readBoletins(json)

    }

    fun hasConnection(ctx: Context): Boolean{
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return info != null && info.isConnected
    }
}