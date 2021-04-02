package com.projects.aldajo92.mercadolibreproducts.data.repository.favorites

import com.projects.aldajo92.mercadolibreproducts.data.datasource.DBDataSource
import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityListMapper
import com.projects.aldajo92.mercadolibreproducts.domain.Product

class FavoritesRepositoryImpl<T> constructor(
    private val dbDataSource: DBDataSource<T>,
    private val listMapper: EntityListMapper<T, Product>
): FavoritesRepository<Product> {

    override suspend fun getFavoritesList() : List<Product> {
        return listMapper.map(dbDataSource.getStoredData())
    }

    override suspend fun saveToFavorites(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavorites(product: Product) {
        TODO("Not yet implemented")
    }

}