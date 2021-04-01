package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.aldajo92.mercadolibreproducts.data.repository.MeliProductsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashBoardViewModel @Inject constructor(
    private val meliProductsRepository: MeliProductsRepository
) : ViewModel() {

    private val _response = MutableLiveData<DashBoardEvent>()
    val response: LiveData<DashBoardEvent> get() = _response

    fun performSearch(keyword: String) {
        viewModelScope.launch {
            try {
                val listResult = meliProductsRepository.getProductsFromSearch(keyword)

                _response.value = DashBoardEvent.ProductsSuccess(listResult)
            } catch (e: Exception) {
                _response.value = DashBoardEvent.ErrorMessage("Failure: " + e.message)
            }
        }
    }
}