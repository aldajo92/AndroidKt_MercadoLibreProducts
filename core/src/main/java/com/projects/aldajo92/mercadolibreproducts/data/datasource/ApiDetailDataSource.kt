package com.projects.aldajo92.mercadolibreproducts.data.datasource

interface ApiDetailDataSource<T, S> {
    suspend fun getProductDetail(keywords: String): T?

    suspend fun getProductDetailDescription(keywords: String): S?
}
