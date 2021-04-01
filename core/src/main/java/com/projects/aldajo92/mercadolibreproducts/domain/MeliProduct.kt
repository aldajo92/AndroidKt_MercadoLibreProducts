package com.projects.aldajo92.mercadolibreproducts.domain

import java.io.Serializable

data class MeliProduct(
    val title: String,
    val price: Int,
    val isFavorite: Boolean = false
) : Serializable {
    companion object {
        val EMPTY = MeliProduct("", 0, false)
    }
}