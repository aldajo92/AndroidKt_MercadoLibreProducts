package com.projects.aldajo92.mercadolibreproducts.presentation.ui.splash

import com.projects.aldajo92.mercadolibreproducts.domain.Country
import com.projects.aldajo92.mercadolibreproducts.presentation.events.DataEvent

sealed class CountryEvent<out T>(val content: T?) : DataEvent<T>(content) {

    class CountriesSuccess(productModels: List<Country>?) :
        CountryEvent<List<Country>>(productModels)

    class ErrorMessage(message: String) : CountryEvent<String>(message)

}
