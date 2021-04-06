package com.projects.aldajo92.mercadolibreproducts.framework.network.datasources

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiDetailDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDescriptionResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDetailResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliApiService

class MeliDetailDataSourceImpl constructor(
    private val meliApiService: MeliApiService
) : ApiDetailDataSource<ProductDetailResponse, ProductDescriptionResponse> {

    override suspend fun getProductDetail(keywords: String): ProductDetailResponse? {
        if (keywords.isBlank()) return null
        return meliApiService.getProductDetail(keywords)
    }

    override suspend fun getProductDetailDescription(keywords: String): ProductDescriptionResponse? {
        if (keywords.isBlank()) return null
        return meliApiService.getProductDescription(keywords)
    }

}
