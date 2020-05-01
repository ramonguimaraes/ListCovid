package com.ramonguimaraes.listcovid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalhes.*

class activity_detalhes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)
        val boletim = intent.getParcelableExtra<Boletim>("boletim");

        detalhesConfirmados.text = boletim.confirmados.toString()
        detalhesCurados.text = boletim.curados.toString()
        detalhesData.text = boletim.data
        detalhesDescartados.text = boletim.descartados.toString()
        detalhesDomiciliar.text = boletim.sDomiciliar.toString()
        detalhesHora.text = boletim.hora
        detalhesHospitalar.text = boletim.sHospitalar.toString()
        detalhesMonitoramento.text = boletim.monitoramento.toString()
        detalhesMortes.text = boletim.mortes.toString()
        detalhesSuspeitos.text = boletim.suspeitos.toString()

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
