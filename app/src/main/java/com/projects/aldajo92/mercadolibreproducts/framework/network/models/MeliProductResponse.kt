package com.projects.aldajo92.mercadolibreproducts.framework.network.models

import com.squareup.moshi.Json

data class MeliProductResponse(
    val id : String,
    val title : String,
    val price : Int,
    @Json(name = "currency_id") val currency : String,
    @Json(name = "available_quantity") val stock : Int
)
