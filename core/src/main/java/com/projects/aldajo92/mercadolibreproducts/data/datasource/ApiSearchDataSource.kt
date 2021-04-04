package com.projects.aldajo92.mercadolibreproducts.data.datasource

interface ApiSearchDataSource<T> {
    suspend fun getProductsFromSearch(keywords: String, offset: Int): List<T>
}
