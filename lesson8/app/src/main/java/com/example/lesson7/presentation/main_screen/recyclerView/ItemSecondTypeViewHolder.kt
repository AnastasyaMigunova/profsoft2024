package com.example.lesson7.presentation.main_screen.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson7.R
import com.example.lesson7.databinding.SecondTypeItemBinding
import com.example.lesson7.presentation.main_screen.models.BigItem

class ItemSecondTypeViewHolder(
    parent: ViewGroup,
    private val itemClickListener: ItemClickListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.second_type_item, parent, false),
) {
    private val binding by viewBinding(SecondTypeItemBinding::bind)

    fun bind(bigItem: BigItem) {
        binding.textViewFirstType.text = bigItem.text
        binding.cardView.setOnClickListener {
            itemClickListener.onSecondTypeItemClick(bigItem)
        }
    }
}