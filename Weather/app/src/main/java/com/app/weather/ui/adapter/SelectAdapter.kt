package com.app.weather.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.weather.ui.activity.DetailActivity
import java.io.Serializable

class SelectAdapter(
    var context: Context,
    var datas: MutableList<MutableMap<String, Any>>,
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ResultViewHolder(SelectView(parent.context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView = holder.itemView as SelectView
        val date = datas[position].get("date").toString()
        itemView.bindView(date)
    }

    override fun getItemCount(): Int = datas.size

    inner class ResultViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                var intent = Intent(context,DetailActivity::class.java)
                intent.putExtra("date",datas[adapterPosition].get("date").toString())
                intent.putExtra("msg",datas[adapterPosition] as Serializable)
                context.startActivity(intent)
            }
        }
    }

}