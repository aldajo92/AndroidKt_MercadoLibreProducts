package com.projects.aldajo92.mercadolibreproducts.framework.db

data class MeliProductEntity(
    val id : String,
    val title : String,
    val price : Int,
    val currency : String,
    val stock : Int
)