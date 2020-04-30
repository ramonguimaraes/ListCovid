package com.ramonguimaraes.listcovid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(){

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    constructList()
    swipe_refresh_layout.setOnRefreshListener {
      constructList()
    }
  }

  fun constructList(){
    var list: ArrayList<Boletim>? = arrayListOf()
    CoroutineScope(IO).launch {
      list = downloadData()

      CoroutineScope(Main).launch {
        loadList(list)
      }
    }
  }

  fun loadList(list: ArrayList<Boletim>?){
    if (list.isNullOrEmpty()){
      mensagem.text = "erro inesperado (lista vazia)"
      progressBar.visibility = View.GONE
      swipe_refresh_layout.isRefreshing = false
    }else{
      progressBar.visibility = View.GONE
      mensagem.visibility = View.GONE
      lst_main.adapter = BoletimAdapter(list)
      lst_main.layoutManager = LinearLayoutManager(this)
      BoletimAdapter(list).notifyDataSetChanged()
      swipe_refresh_layout.isRefreshing = false
    }
  }

  suspend fun downloadData():ArrayList<Boletim>?{
    var boletins: ArrayList<Boletim>? = arrayListOf()
    if(BoletimHttp.hasConnection(this)){
      boletins!!.clear()
      boletins = BoletimHttp.loadBoletim()
    }else{
      mensagem.text = "Sem internet"
    }

    return boletins
  }
}

