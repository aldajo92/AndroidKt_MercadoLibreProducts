package com.projects.aldajo92.mercadolibreproducts.data.repository.search

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiSearchDataSource
import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityListMapper
import com.projects.aldajo92.mercadolibreproducts.domain.Product

class SearchRepositoryImpl<T> constructor(
    private val apiSearch: ApiSearchDataSource<T>,
    private val listMapper: EntityListMapper<T, Product>
) : SearchRepository<Product> {

    override suspend fun getProductsFromSearch(keywords: String): List<Product>? {
        if (keywords.isEmpty()) return null
        return listMapper.map(apiSearch.getProductsFromSearch(keywords))
    }

}
