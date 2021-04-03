package com.projects.aldajo92.mercadolibreproducts.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.aldajo92.mercadolibreproducts.data.repository.favorites.FavoritesRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository<Product>
) : ViewModel() {

    lateinit var product: Product

    fun saveToFavorites() {
        viewModelScope.launch {
            favoritesRepository.saveToFavorites(product)
        }
    }

}