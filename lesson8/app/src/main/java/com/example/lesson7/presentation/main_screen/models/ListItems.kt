package com.example.lesson7.presentation.main_screen.models

sealed class ListItems {
    data class SmallItemInfo(val smallInfo: SmallItem) : ListItems()

    data class BigItemInfo(val bigInfo: BigItem) : ListItems()
}
