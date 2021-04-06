package com.projects.aldajo92.mercadolibreproducts.framework.network.datasources

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiSearchDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.ProductResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliApiService

class MeliSearchDataSourceImpl constructor(
    private val meliApiService: MeliApiService
) : ApiSearchDataSource<ProductResponse> {

    override suspend fun getProductsFromSearch(countryId: String, keywords: String, offset: Int): List<ProductResponse> {
        if (keywords.isBlank()) return emptyList()
        return meliApiService.getProductsFromSearch(countryId, keywords, offset).productList
    }

}
