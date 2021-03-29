package com.projects.aldajo92.mercadolibreproducts.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.aldajo92.mercadolibreproducts.network.MeliApi
import com.projects.aldajo92.mercadolibreproducts.ui.dashboard.DashBoardEvent
import kotlinx.coroutines.launch

class DashBoardViewModel : ViewModel() {

    private val _response = MutableLiveData<DashBoardEvent>()
    val response: LiveData<DashBoardEvent> get() = _response

    fun getMeliResponse() {
        viewModelScope.launch {
            try {
                val listResult = MeliApi.retrofitService.getProducts()
//                _response.value = "Success: ${listResult.results.size} products retrieved"
                _response.value = DashBoardEvent.ProductsSuccess(listResult.results)
            } catch (e: Exception) {
                _response.value = DashBoardEvent.ErrorMessage("Failure: " + e.message)
            }
        }
    }
}