package com.projects.aldajo92.mercadolibreproducts.framework.db

import com.projects.aldajo92.mercadolibreproducts.data.datasource.DBDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.db.entities.ProductEntity

class MeliDBDataSource : DBDataSource<ProductEntity> {
    override suspend fun getStoredData(): List<ProductEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun storeData(data: ProductEntity) {
        TODO("Not yet implemented")
    }
}