package com.projects.aldajo92.mercadolibreproducts.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.projects.aldajo92.mercadolibreproducts.framework.db.entities.FavoriteProductEntity

@Dao
interface FavoriteProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteProduct(product: FavoriteProductEntity)

    @Query("DELETE FROM favorites WHERE meliId = :key")
    suspend fun deleteById(key: String)

    @Query("DELETE FROM favorites")
    suspend fun deleteAll()

    @Query("SELECT * FROM favorites")
    suspend fun getAllProducts(): List<FavoriteProductEntity>

    @Query("SELECT * FROM favorites WHERE meliId = :key")
    suspend fun getFavoriteProduct(key: String): FavoriteProductEntity

}
