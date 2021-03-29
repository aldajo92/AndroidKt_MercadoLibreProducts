package com.projects.aldajo92.mercadolibreproducts.models

data class MeliResult(
    val paging : MeliPaging,
    val results : List<MeliProduct>
)
