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
    var list: ArrayList<Boletim>?

    if(BoletimHttp.hasConnection(this)){
      CoroutineScope(IO).launch {
        list = downloadData()
        CoroutineScope(Main).launch {
          loadList(list)
        }
      }

    }else{
      showError("sem internet", false)

    }
  }


  fun showError(msg: String, showProg: Boolean){
    if(msg == "" && !showProg){
      mensageBox.visibility = View.GONE

    }else if(!showProg){
      mensagem.text = msg
      progressBar.visibility = View.GONE

    }
  }

  fun loadList(list: ArrayList<Boletim>?){
    if (list.isNullOrEmpty()){
      showError("Lista de boletins vazia", false)
      swipe_refresh_layout.isRefreshing = false

    }else{
      showError("", false)
      lst_main.adapter = BoletimAdapter(list)
      lst_main.layoutManager = LinearLayoutManager(this)
      BoletimAdapter(list).notifyDataSetChanged()
      swipe_refresh_layout.isRefreshing = false

    }
  }

  suspend fun downloadData():ArrayList<Boletim>?{
    var boletins: ArrayList<Boletim>? = arrayListOf()
    boletins!!.clear()
    boletins = BoletimHttp.loadBoletim()
    return boletins

  }
}

