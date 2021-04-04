package com.projects.aldajo92.mercadolibreproducts.data.repository.detail

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiDetailDataSource
import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityMapper
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDescription
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDetail

class DetailRepositoryImpl<T, S> constructor(
    private val apiDetailSearch: ApiDetailDataSource<T, S>,
    private val mapper: EntityMapper<T, ProductDetail>,
    private val mapperDescription: EntityMapper<S, ProductDescription>
) : DetailRepository<ProductDetail, ProductDescription> {

    override suspend fun getProductDetail(id: String): ProductDetail? {
        if (id.isEmpty()) return null
        return mapper.map(apiDetailSearch.getProductDetail(id))
    }

    override suspend fun getProductDescription(id: String): ProductDescription? {
        if (id.isEmpty()) return null
        return mapperDescription.map(apiDetailSearch.getProductDetailDescription(id))
    }

}
