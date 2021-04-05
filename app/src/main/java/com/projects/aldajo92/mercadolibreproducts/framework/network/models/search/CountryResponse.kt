package com.projects.aldajo92.mercadolibreproducts.framework.network.models.search

import com.squareup.moshi.Json

data class CountryResponse(
    @Json(name = "default_currency_id") val currencyId: String = "",
    @Json(name = "id") val meliCountryId: String = "",
    val name: String = ""
)