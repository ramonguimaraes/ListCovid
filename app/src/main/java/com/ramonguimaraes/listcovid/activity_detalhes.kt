package com.ramonguimaraes.listcovid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalhes.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class activity_detalhes : AppCompatActivity() {

    var lista: ArrayList<Boletim> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)
        val position = intent.getIntExtra("position", 0);

        CoroutineScope(IO).launch {
            lista = downloadData()
            if(!lista.isNullOrEmpty()){
                CoroutineScope(Main).launch {
                        detalhesConfirmados.text = lista[position].confirmados.toString()
                        detalhesCurados.text = lista[position].curados.toString()
                        detalhesData.text = lista[position].data
                        detalhesDescartados.text = lista[position].descartados.toString()
                        detalhesDomiciliar.text = lista[position].sDomiciliar.toString()
                        detalhesHora.text = lista[position].hora
                        detalhesHospitalar.text = lista[position].sHospitalar.toString()
                        detalhesMonitoramento.text = lista[position].monitoramento.toString()
                        detalhesMortes.text = lista[position].mortes.toString()
                        detalhesSuspeitos.text = lista[position].suspeitos.toString()
                }
            }
        }

        val actionbar = supportActionBar

        actionbar!!.title = "Boletim Detalhado"

        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    suspend fun downloadData():ArrayList<Boletim>{
        if(BoletimHttp.hasConnection(this)){
            lista.clear()
            lista = BoletimHttp.loadBoletim()!!
        }
        return lista
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
