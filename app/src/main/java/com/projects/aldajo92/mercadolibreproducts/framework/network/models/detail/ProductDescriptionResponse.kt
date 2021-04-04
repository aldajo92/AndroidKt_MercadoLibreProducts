package com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail

import com.squareup.moshi.Json

data class ProductDescriptionResponse(
    @Json(name = "plain_text") val description: String = "",
    val last_updated: String = "",
    val date_created: String = ""
)
