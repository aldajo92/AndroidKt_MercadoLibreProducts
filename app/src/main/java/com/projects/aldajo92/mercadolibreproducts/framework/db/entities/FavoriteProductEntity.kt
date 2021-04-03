package com.projects.aldajo92.mercadolibreproducts.framework.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteProductEntity(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val title : String,
    val price : Int,
    val currency : String,
    val stock : Int
)