package com.ramonguimaraes.listcovid


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalhes.*

class activity_detalhes : AppCompatActivity() {

    var lista = arrayListOf<Boletim>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)
        val position = intent.getIntExtra("position", 0);

        val jsonParser = JsonParser()

        jsonParser.readJson(this, lista)

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

        val actionbar = supportActionBar

        actionbar!!.title = "Boletim Detalhado"

        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
