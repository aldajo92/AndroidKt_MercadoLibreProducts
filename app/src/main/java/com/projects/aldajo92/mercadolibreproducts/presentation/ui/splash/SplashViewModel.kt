package com.projects.aldajo92.mercadolibreproducts.presentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.aldajo92.mercadolibreproducts.data.repository.country.CountryRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Country
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SplashViewModel @Inject constructor(
    private val countryRepository: CountryRepository<Country>
) : ViewModel() {

    private val _countriesLiveData = MutableLiveData<CountryEvent<*>>()
    val countriesLiveData: LiveData<CountryEvent<*>> get() = _countriesLiveData

    fun getCountries() {
        viewModelScope.launch {
            val countries = countryRepository.getCountries()
            _countriesLiveData.value = CountryEvent.CountriesSuccess(countries)
        }
    }

    fun setCountry(country: Country) {
        countryRepository.setSelectedCountry(country)
    }

}
