package com.projects.aldajo92.mercadolibreproducts.data.datasource

interface ApiDataSource<T> {
    suspend fun getProductsFromSearch(keywords: String): List<T>
}
