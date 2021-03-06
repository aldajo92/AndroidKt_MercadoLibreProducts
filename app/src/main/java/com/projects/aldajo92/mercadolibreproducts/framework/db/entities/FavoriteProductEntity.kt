package com.projects.aldajo92.mercadolibreproducts.framework.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteProductEntity(
    @PrimaryKey val meliId : String,
    val title : String,
    val price : Double,
    val currency : String,
    val stock : Int,
    val description: String,
    val imgId: String = "",
    val productUrl: String = ""
)
