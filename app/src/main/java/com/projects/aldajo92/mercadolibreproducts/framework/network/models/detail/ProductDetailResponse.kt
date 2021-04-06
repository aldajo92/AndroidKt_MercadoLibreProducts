package com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail

data class ProductDetailResponse(
    val id: String = "",
    val initial_quantity: Int = 0,
    val available_quantity: Int = 0,
    val sold_quantity: Int = 0,
    val condition: String? = "",
    val pictures: List<PictureProductResponse> = emptyList(),
    val accepts_mercadopago: Boolean = false,
    val warranty: String? = ""
)
