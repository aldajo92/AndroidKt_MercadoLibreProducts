package com.projects.aldajo92.mercadolibreproducts.data.datasource

interface CountriesDataSource<T> {
    suspend fun getCountries(): List<T>?
}
