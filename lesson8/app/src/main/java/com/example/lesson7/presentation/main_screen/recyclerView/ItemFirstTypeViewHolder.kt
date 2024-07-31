package com.example.lesson7.presentation.main_screen.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson7.R
import com.example.lesson7.databinding.FirstTypeItemBinding
import com.example.lesson7.presentation.main_screen.models.SmallItem

class ItemFirstTypeViewHolder(
    parent: ViewGroup,
    private val itemClickListener: ItemClickListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.first_type_item, parent, false),
) {
    private val binding by viewBinding(FirstTypeItemBinding::bind)

    fun bind(smallItem: SmallItem) {
        binding.textViewFirstType.text = smallItem.text
        binding.textViewFirstType.setOnClickListener {
            itemClickListener.onFirstTypeItemClick(smallItem)
        }
    }
}