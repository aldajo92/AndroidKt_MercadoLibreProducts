package com.projects.aldajo92.mercadolibreproducts.framework.network

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiSearchDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.ProductResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliProductService

class MeliSearchDataSource constructor(
    private val meliProductService: MeliProductService
) : ApiSearchDataSource<ProductResponse> {

    override suspend fun getProductsFromSearch(keywords: String): List<ProductResponse> {
        if (keywords.isBlank()) return emptyList()
        return meliProductService.getProductsFromSearch(keywords).productList
    }

}
