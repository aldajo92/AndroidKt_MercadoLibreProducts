package com.projects.aldajo92.mercadolibreproducts.domain

import java.io.Serializable

data class Product(
    val meliId: String,
    val title: String,
    val price: Int,
    val isFavorite: Boolean = false,
    val imgUrl: String = "",
    val imgId: String = "",
    val productUrl: String = "",
    val currency: String = ""
) : Serializable {
    companion object {
        val EMPTY = Product("0", "", 0, false)
    }
}
