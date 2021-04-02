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

    private val _response = MutableLiveData<DashBoardEvents>()
    val response: LiveData<DashBoardEvents> get() = _response

    fun performSearch(keyword: String) {
        viewModelScope.launch {
            try {
                val listResult = repository.getProductsFromSearch(keyword)

                _response.value = DashBoardEvents.ProductsSuccess(listResult)
            } catch (e: Exception) {
                _response.value = DashBoardEvents.ErrorMessage("Failure: " + e.message)
            }
        }
    }
}