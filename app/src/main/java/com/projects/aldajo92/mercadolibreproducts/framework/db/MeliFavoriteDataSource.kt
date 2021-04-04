package com.projects.aldajo92.mercadolibreproducts.framework.db

import com.projects.aldajo92.mercadolibreproducts.data.datasource.DBDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.db.dao.FavoriteProductsDao
import com.projects.aldajo92.mercadolibreproducts.framework.db.entities.FavoriteProductEntity

class MeliFavoriteDataSource(
    private val dao: FavoriteProductsDao
) : DBDataSource<FavoriteProductEntity> {

    override suspend fun getStoredData(): List<FavoriteProductEntity> {
        return dao.getAllProducts()
    }

    override suspend fun getById(id: String): FavoriteProductEntity {
        return dao.getFavoriteProduct(id)
    }

    override suspend fun storeData(data: FavoriteProductEntity) {
        dao.addFavoriteProduct(data)
    }

    override suspend fun removeById(id: String) {
        dao.deleteById(id)
    }

    override suspend fun removeAll() {
        dao.deleteAll()
    }
}
