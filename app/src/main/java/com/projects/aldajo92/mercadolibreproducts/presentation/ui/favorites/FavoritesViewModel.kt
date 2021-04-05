package com.projects.aldajo92.mercadolibreproducts.presentation.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.aldajo92.mercadolibreproducts.data.repository.favorites.FavoritesRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.events.DashBoardEvents
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository<Product>
) : ViewModel() {

    private val _productItems = mutableListOf<Product>()
    val productItems: List<Product> get() = _productItems

    private val _responseLiveData = MutableLiveData<DashBoardEvents<*>>()
    val responseLiveData: LiveData<DashBoardEvents<*>> get() = _responseLiveData

    fun getFavoriteProducts() {
        viewModelScope.launch {
            val favoritesList = favoritesRepository.getFavoritesList()
            _responseLiveData.value = DashBoardEvents.ProductsSuccess(favoritesList)
            _productItems.clear()
            _productItems.addAll(favoritesList)
        }
    }

}
