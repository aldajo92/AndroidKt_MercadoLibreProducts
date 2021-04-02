package com.projects.aldajo92.mercadolibreproducts.data.repository

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiProductDataSource
import com.projects.aldajo92.mercadolibreproducts.data.datasource.DBProductDataSource
import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityListMapper
import com.projects.aldajo92.mercadolibreproducts.domain.Product

class SearchProductsRepositoryImpl<T, S> constructor(
    private val api: ApiProductDataSource<T>,
    private val database: DBProductDataSource<S>,
    private val productListEntityMapper: EntityListMapper<T, Product>
) : SearchProductsRepository<Product> {

    override suspend fun getProductsFromSearch(keywords: String): List<Product> {
        val result = api.getProductsFromSearch(keywords)
        return productListEntityMapper.map(result)
    }

    private fun getCachedProduct(): List<Product> {
        return emptyList()
    }

    private fun clearCache() {

    }

}
