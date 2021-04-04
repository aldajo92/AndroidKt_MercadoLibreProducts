package com.projects.aldajo92.mercadolibreproducts.framework.network.datasources

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiSearchDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.ProductResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliProductService

class MeliSearchDataSourceImpl constructor(
    private val meliProductService: MeliProductService
) : ApiSearchDataSource<ProductResponse> {

    override suspend fun getProductsFromSearch(keywords: String, offset: Int): List<ProductResponse> {
        if (keywords.isBlank()) return emptyList()
        return meliProductService.getProductsFromSearch(keywords, offset).productList
    }

}
