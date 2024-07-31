package com.example.lesson7.presentation.main_screen.recyclerView

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson7.presentation.main_screen.models.BigItem
import com.example.lesson7.presentation.main_screen.models.ListItems
import com.example.lesson7.presentation.main_screen.models.SmallItem
import kotlin.random.Random

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items = mutableListOf<ListItems>()
    lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FIRST_TYPE -> ItemFirstTypeViewHolder(parent, itemClickListener)
            SECOND_TYPE -> ItemSecondTypeViewHolder(parent, itemClickListener)
            else -> error("ViewType not supported")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemFirstTypeViewHolder -> {
                holder.bind((items[position] as ListItems.SmallItemInfo).smallInfo)
                holder.itemView.setOnClickListener {
                    itemClickListener.onFirstTypeItemClick((items[position] as ListItems.SmallItemInfo).smallInfo)
                }
            }

            is ItemSecondTypeViewHolder -> {
                holder.bind((items[position] as ListItems.BigItemInfo).bigInfo)
                holder.itemView.setOnClickListener {
                    itemClickListener.onSecondTypeItemClick((items[position] as ListItems.BigItemInfo).bigInfo)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is ListItems.SmallItemInfo) FIRST_TYPE else SECOND_TYPE
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(infoItems: List<ListItems>) {
        this.items.clear()
        this.items.addAll(infoItems)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItem() {
        val newItem: ListItems = if (Random.nextBoolean()) {
            ListItems.SmallItemInfo(SmallItem("Small Item " + (items.size + 1)))
        } else {
            ListItems.BigItemInfo(BigItem("Big Item " + (items.size + 1)))
        }
        items.add(newItem)
        notifyItemInserted(items.size - 1)
    }

    companion object {
        private const val FIRST_TYPE = 0
        private const val SECOND_TYPE = 1
    }
}