package com.projects.aldajo92.mercadolibreproducts.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.projects.aldajo92.mercadolibreproducts.framework.db.dao.FavoriteProductsDao
import com.projects.aldajo92.mercadolibreproducts.framework.db.entities.FavoriteProductEntity

@Database(entities = [FavoriteProductEntity::class], version = 1)
abstract class MeliFavoritesDatabase : RoomDatabase() {
    abstract fun favoriteProductDao(): FavoriteProductsDao
}
