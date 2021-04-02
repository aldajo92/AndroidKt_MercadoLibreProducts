package com.projects.aldajo92.mercadolibreproducts.domain

data class ProductDetail(
    val title: String,
    val price: Int,
    val isFavorite: Boolean = false
)