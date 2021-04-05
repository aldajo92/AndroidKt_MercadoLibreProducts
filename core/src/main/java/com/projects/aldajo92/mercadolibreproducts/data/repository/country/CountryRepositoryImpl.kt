package com.projects.aldajo92.mercadolibreproducts.data.repository.country

import com.projects.aldajo92.mercadolibreproducts.data.datasource.CountriesDataSource
import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityListMapper
import com.projects.aldajo92.mercadolibreproducts.domain.Country

class CountryRepositoryImpl<T> constructor(
    private val countryDataSource: CountriesDataSource<T>,
    private val mapper: EntityListMapper<T, Country>
) : CountryRepository<Country> {

    private var selectedCountry: Country? = null

    override suspend fun getCountries(): List<Country> {
        return mapper.map(countryDataSource.getCountries() ?: emptyList())
    }

    override fun setSelectedCountry(country: Country) {
        selectedCountry = country
    }

    override fun getSelectedCountry(): Country? {
        return selectedCountry
    }

}
