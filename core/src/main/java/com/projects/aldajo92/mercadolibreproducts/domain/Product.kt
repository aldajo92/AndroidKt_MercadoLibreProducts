package com.projects.aldajo92.mercadolibreproducts.domain

import java.io.Serializable

data class Product(
    val title: String,
    val price: Int,
    val isFavorite: Boolean = false
) : Serializable {
    companion object {
        val EMPTY = Product("", 0, false)
    }
}