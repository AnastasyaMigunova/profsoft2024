package com.example.lesson7.presentation.main_screen.viewPager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson7.R
import com.example.lesson7.databinding.ViewPagerItemBinding

class ViewPagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item, parent, false)
) {

    private val binding by viewBinding(ViewPagerItemBinding::bind)

    fun bind(text: String) {
        binding.textViewItem.text = text
    }
}