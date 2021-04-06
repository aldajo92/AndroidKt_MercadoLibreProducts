package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.aldajo92.mercadolibreproducts.data.repository.search.SearchRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.events.DashBoardEvents
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashBoardViewModel @Inject constructor(
    private val repository: SearchRepository<Product>
) : ViewModel() {

    private val _productItems = mutableListOf<Product>()
    val productItems: List<Product> get() = _productItems

    private val _responseLiveData = MutableLiveData<DashBoardEvents<*>>()
    val responseLiveData: LiveData<DashBoardEvents<*>> get() = _responseLiveData

    private var keyword: String? = null

    fun performFirstSearch(keyword: String) {
        if (keyword.isEmpty()) return

        this.keyword = keyword
        viewModelScope.launch {
            try {
                val listResult = repository.getProductsFromSearch(keyword, 0) ?: emptyList()
                _responseLiveData.value = DashBoardEvents.ProductsSuccess(listResult)
                _productItems.clear()
                _productItems.addAll(listResult)
            } catch (e: Exception) {
//                 _responseLiveData.value = DashBoardEvents.ErrorMessage("Failure: " + e.message)
            }
        }
    }

    fun getProductsByPagination(offset: Int) {
        viewModelScope.launch {
            try {
                keyword?.let {
                    val listResult = repository.getProductsFromSearch(it, offset) ?: emptyList()
                    _responseLiveData.value = DashBoardEvents.ProductsPaginationSuccess(listResult)
                    _productItems.addAll(listResult)
                }
            } catch (e: Exception) {
                 _responseLiveData.value = DashBoardEvents.ErrorMessage("Failure: " + e.message)
            }
        }
    }

    fun clearAll() {
        keyword = ""
        _responseLiveData.value = DashBoardEvents.ProductsSuccess(emptyList())
        _productItems.clear()
    }
}
