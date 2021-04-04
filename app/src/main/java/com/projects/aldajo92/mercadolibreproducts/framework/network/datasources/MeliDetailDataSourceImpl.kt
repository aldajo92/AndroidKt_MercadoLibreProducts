package com.projects.aldajo92.mercadolibreproducts.framework.network.datasources

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiDetailDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDescriptionResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDetailResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliProductService

class MeliDetailDataSourceImpl constructor(
    private val meliProductService: MeliProductService
) : ApiDetailDataSource<ProductDetailResponse, ProductDescriptionResponse> {

    override suspend fun getProductDetail(keywords: String): ProductDetailResponse? {
        if (keywords.isBlank()) return null
        return meliProductService.getProductDetail(keywords)
    }

    override suspend fun getProductDetailDescription(keywords: String): ProductDescriptionResponse? {
        if (keywords.isBlank()) return null
        return meliProductService.getProductDescription(keywords)
    }

}
