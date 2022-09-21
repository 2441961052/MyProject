package com.app.weather.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DetailsAdapter(
    var context: Context,
    var msg: MutableMap<String, Any>,
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val list = msg.keys.distinct()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ResultViewHolder(DetailView(parent.context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView = holder.itemView as DetailView
        val title = list[position]
        val msg = msg.get(list[position]).toString()
        itemView.bindView(title,msg)
    }

    override fun getItemCount(): Int = list.size

    inner class ResultViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView)

}