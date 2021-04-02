package com.projects.aldajo92.mercadolibreproducts.framework.network

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.ProductResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliProductService

class MeliSearchDataSource constructor(
    private val meliProductService: MeliProductService
) : ApiDataSource<ProductResponse> {

    override suspend fun getProductsFromSearch(keywords: String): List<ProductResponse> {
        return meliProductService.getProductsFromSearch(keywords).productList
    }

}