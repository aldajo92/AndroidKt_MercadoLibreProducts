package com.projects.aldajo92.mercadolibreproducts.data.repository.search

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiSearchDataSource
import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityListMapper
import com.projects.aldajo92.mercadolibreproducts.data.repository.country.CountryRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Country
import com.projects.aldajo92.mercadolibreproducts.domain.Product

class SearchRepositoryImpl<T> constructor(
    private val countryRepository: CountryRepository<Country>,
    private val apiSearch: ApiSearchDataSource<T>,
    private val listMapper: EntityListMapper<T, Product>,
) : SearchRepository<Product> {

    override suspend fun getProductsFromSearch(keywords: String, offset: Int): List<Product>? {
        if (keywords.isEmpty()) return null
        return countryRepository.getSelectedCountry()?.let {
            listMapper.map(apiSearch.getProductsFromSearch(it.countryId, keywords, offset))
        }
    }

}
