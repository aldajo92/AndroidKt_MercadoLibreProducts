package com.projects.aldajo92.mercadolibreproducts.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.aldajo92.mercadolibreproducts.network.MeliApiService
import com.projects.aldajo92.mercadolibreproducts.ui.fragments.dashboard.DashBoardEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashBoardViewModel @Inject constructor(
    val meliApiService: MeliApiService
) : ViewModel() {

    private val _response = MutableLiveData<DashBoardEvent>()
    val response: LiveData<DashBoardEvent> get() = _response

    fun getMeliResponse() {
        viewModelScope.launch {
            try {
                val listResult = meliApiService.getProducts()
                _response.value = DashBoardEvent.ProductsSuccess(listResult.results)
            } catch (e: Exception) {
                _response.value = DashBoardEvent.ErrorMessage("Failure: " + e.message)
            }
        }
    }
}