package com.app.weather.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.app.weather.databinding.ItemDetailsBinding
import com.app.weather.databinding.ItemSelectBinding

class SelectView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    var binding = ItemSelectBinding.inflate(LayoutInflater.from(context), this, true)

    fun bindView(string: String) {
        binding.itemTitle.text = string
    }

}