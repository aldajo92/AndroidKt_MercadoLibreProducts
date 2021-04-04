package com.projects.aldajo92.mercadolibreproducts.framework.network.models.search

import com.squareup.moshi.Json

data class ProductResponse(
    val id: String,
    val title: String,
    val price: Int,
    @Json(name = "currency_id") val currency: String? = "",
    @Json(name = "available_quantity") val stock: Int,
    val thumbnail: String? = "",
    @Json(name = "thumbnail_id") val thumbnailId: String? = "",
    @Json(name = "permalink") val meliLink: String = ""
)
