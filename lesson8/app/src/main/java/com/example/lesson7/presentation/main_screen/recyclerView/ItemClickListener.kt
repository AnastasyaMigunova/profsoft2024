package com.example.lesson7.presentation.main_screen.recyclerView

import com.example.lesson7.presentation.main_screen.models.BigItem
import com.example.lesson7.presentation.main_screen.models.SmallItem

interface ItemClickListener {
    fun onFirstTypeItemClick(smallItem: SmallItem)
    fun onSecondTypeItemClick(bigItem: BigItem)
}