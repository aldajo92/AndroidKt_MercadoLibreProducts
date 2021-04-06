package com.projects.aldajo92.mercadolibreproducts.framework.network

import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityListMapper
import com.projects.aldajo92.mercadolibreproducts.domain.Country
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.CountryResponse
import javax.inject.Inject

class CountryResponseMapper @Inject constructor() :
    EntityListMapper<CountryResponse, Country> {

    override fun map(inputValue: List<CountryResponse>): List<Country> {
        return inputValue.map {
            Country(it.name, it.currencyId, it.meliCountryId)
        }
    }
}
