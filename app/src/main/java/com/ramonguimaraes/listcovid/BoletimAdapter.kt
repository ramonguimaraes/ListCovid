package com.ramonguimaraes.listcovid

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class BoletimAdapter(val listaBoletins: ArrayList<Boletim>?): RecyclerView.Adapter<BoletimAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(boletim: Boletim) {
            itemView.txt_item_data.text = boletim.data
            itemView.txt_item_suspeitos.text = boletim.suspeitos.toString()
            itemView.txt_item_confirmados.text = boletim.confirmados.toString()
        }
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        val vh = ViewHolder(view)

        val context: Context = view.context

        val intent = Intent(context, activity_detalhes::class.java)

        vh.itemView.setOnClickListener {
            intent.putExtra("position", vh.adapterPosition)
            context.startActivity(intent)
        }

        return vh
    }

    override fun getItemCount(): Int {
         return listaBoletins!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(listaBoletins!![position])
    }
}
