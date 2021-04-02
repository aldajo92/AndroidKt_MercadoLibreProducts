package com.projects.aldajo92.mercadolibreproducts.data.datasource

interface ApiProductDataSource<T> {
    suspend fun getProductsFromSearch(keywords: String): List<T>
}