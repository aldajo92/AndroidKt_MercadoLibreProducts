package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.aldajo92.mercadolibreproducts.data.repository.search.SearchRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashBoardViewModel @Inject constructor(
    private val repository: SearchRepository<Product>
) : ViewModel() {

    private val _productItems = mutableListOf<Product>()
    val productItems: List<Product> get() = _productItems

    private val _response = MutableLiveData<DashBoardEvents<*>>()
    val response: LiveData<DashBoardEvents<*>> get() = _response

    private var keyword: String? = null

    fun performSearch(keyword: String) {
        if (this.keyword == keyword) return

        this.keyword = keyword
        viewModelScope.launch {
            try {
                val listResult = repository.getProductsFromSearch(keyword, 0) ?: emptyList()
                _response.value = DashBoardEvents.ProductsSuccess(listResult)
                _productItems.clear()
                _productItems.addAll(listResult)
            } catch (e: Exception) {
                _response.value = DashBoardEvents.ErrorMessage("Failure: " + e.message)
            }
        }
    }

    fun getProductsByPagination(offset: Int) {
        viewModelScope.launch {
            try {
                keyword?.let {
                    val listResult = repository.getProductsFromSearch(it, offset) ?: emptyList()
                    _response.value = DashBoardEvents.ProductsPaginationSuccess(listResult)
                    _productItems.addAll(listResult)
                }
            } catch (e: Exception) {
                _response.value = DashBoardEvents.ErrorMessage("Failure: " + e.message)
            }
        }
    }
}
