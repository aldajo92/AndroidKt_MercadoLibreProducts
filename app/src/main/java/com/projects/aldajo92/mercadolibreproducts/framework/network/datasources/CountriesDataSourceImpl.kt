package com.projects.aldajo92.mercadolibreproducts.framework.network.datasources

import com.projects.aldajo92.mercadolibreproducts.data.datasource.CountriesDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.CountryResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliApiService

class CountriesDataSourceImpl constructor(
    private val meliApiService: MeliApiService
) : CountriesDataSource<CountryResponse> {

    override suspend fun getCountries(): List<CountryResponse>? {
        return meliApiService.getCountries()
    }

}
