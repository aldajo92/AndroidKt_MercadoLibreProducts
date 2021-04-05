package com.projects.aldajo92.mercadolibreproducts.presentation.ui.favorites

import androidx.lifecycle.ViewModel
import com.projects.aldajo92.mercadolibreproducts.data.repository.favorites.FavoritesRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository<Product>
) : ViewModel() {



}
