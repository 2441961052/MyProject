package com.app.weather.ui.adapter

import android.content.Context
import android.icu.text.CaseMap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.app.weather.databinding.ItemSerchBinding

class SerchView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    var binding = ItemSerchBinding.inflate(LayoutInflater.from(context), this, true)

    fun bindView(title: String, msg: String) {
        binding.itemTitle.text = title
        binding.itemMsg.text = msg
    }

}