package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.projects.aldajo92.mercadolibreproducts.data.repository.country.CountryRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.search.SearchRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Country
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.events.DashBoardEvents
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashBoardViewModel @Inject constructor(
    private val countryRepository: CountryRepository<Country>,
    private val searchRepository: SearchRepository<Product>
) : ViewModel() {

    private val _productItems = mutableListOf<Product>()
    val productItems: List<Product> get() = _productItems

    private val _responseLiveData = MutableLiveData<DashBoardEvents<*>>()
    val responseLiveData: LiveData<DashBoardEvents<*>> get() = _responseLiveData

    private var keyword: String? = null

    private var lastCountry: String = ""

    fun performFirstSearch(keyword: String) {
        if (keyword.isEmpty()) return

        this.keyword = keyword
        viewModelScope.launch {
            try {
                val listResult = searchRepository.getProductsFromSearch(keyword, 0) ?: emptyList()
                _responseLiveData.value = DashBoardEvents.ProductsSuccess(listResult)
                _productItems.clear()
                _productItems.addAll(listResult)
                lastCountry = countryRepository.getSelectedCountry()?.countryId ?: ""
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
                Timber.e(e)
            }
        }
    }

    fun getProductsByPagination(offset: Int) {
        viewModelScope.launch {
            try {
                keyword?.let {
                    val listResult =
                        searchRepository.getProductsFromSearch(it, offset) ?: emptyList()
                    _responseLiveData.value = DashBoardEvents.ProductsPaginationSuccess(listResult)
                    _productItems.addAll(listResult)
                }
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
                Timber.e(e)
            }
        }
    }

    fun clearAll() {
        keyword = ""
        _responseLiveData.value = DashBoardEvents.ProductsSuccess(emptyList())
        _productItems.clear()
    }

    fun getLastCountry(): String {
        return lastCountry
    }
}
