package com.projects.aldajo92.mercadolibreproducts.data.datasource

interface ApiSearchDataSource<T> {
    suspend fun getProductsFromSearch(countryId: String, keywords: String, offset: Int): List<T>
}
