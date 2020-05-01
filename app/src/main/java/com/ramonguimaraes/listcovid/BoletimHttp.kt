package com.ramonguimaraes.listcovid

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.util.concurrent.TimeUnit

object BoletimHttp {

    val url = "https://raw.githubusercontent.com/ramonsl/ws-covid/master/db.json"

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

        return JsonParser.readBoletins(json)
    }

    fun hasConnection(ctx: Context): Boolean{
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return info != null && info.isConnected
    }
}