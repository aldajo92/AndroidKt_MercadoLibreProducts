package com.projects.aldajo92.mercadolibreproducts.framework.network.models

import com.squareup.moshi.Json

data class MeliSearchResult(
    val paging : MeliPaging? = null,
    @Json(name = "results") val productList : List<MeliProductResponse> = emptyList()
)
