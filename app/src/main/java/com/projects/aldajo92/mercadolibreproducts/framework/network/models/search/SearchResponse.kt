package com.projects.aldajo92.mercadolibreproducts.framework.network.models.search

import com.squareup.moshi.Json

data class SearchResponse(
    val paging : PagingResponse? = null,
    @Json(name = "results") val productList : List<ProductResponse> = emptyList()
)
