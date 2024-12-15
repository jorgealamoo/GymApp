package com.example.gymapp.ui.pointsStore

import com.example.gymapp.R

data class DataItemsStore(
    val name: String,
    val resourceImage: Int
)

object ItemsStoreProvider{
    val Items = listOf(
        DataItemsStore(name = "Canteen", resourceImage = R.drawable.canteen),
        DataItemsStore(name = "Shaker", resourceImage = R.drawable.shaker),
        DataItemsStore(name = "Bag", resourceImage = R.drawable.bag),
        DataItemsStore(name = "Towel", resourceImage = R.drawable.towel),
    )

    fun getImageByName(name: String): Int? {
        return Items.find {it.name == name }?.resourceImage
    }
}