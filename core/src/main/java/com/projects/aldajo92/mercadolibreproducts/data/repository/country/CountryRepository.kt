package com.projects.aldajo92.mercadolibreproducts.data.repository.country

interface CountryRepository<T> {

    suspend fun getCountries(): List<T>?

    fun setSelectedCountry(country: T)

    fun getSelectedCountry(): T?

}
