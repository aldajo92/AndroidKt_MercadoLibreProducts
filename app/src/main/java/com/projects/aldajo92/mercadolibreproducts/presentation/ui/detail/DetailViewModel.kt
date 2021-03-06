package com.projects.aldajo92.mercadolibreproducts.presentation.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.aldajo92.mercadolibreproducts.data.repository.detail.DetailRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.favorites.FavoritesRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDescription
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDetail
import com.projects.aldajo92.mercadolibreproducts.presentation.events.DashBoardEvents
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository<ProductDetail, ProductDescription>,
    private val favoritesRepository: FavoritesRepository<Product>
) : ViewModel() {

    private lateinit var productDetail: ProductDetail
    private lateinit var productDescription: ProductDescription

    val description = ObservableField("")

    val isFavorite = ObservableField(false)

    val productField = ObservableField<Product>()

    private val _eventLiveData = MutableLiveData<DashBoardEvents<*>>()
    val eventLiveData: LiveData<DashBoardEvents<*>> get() = _eventLiveData

    fun setupProductInformation(product: Product, isFavorite: Boolean) {
        this.productField.set(product)
        this.isFavorite.set(isFavorite)

        if (!isFavorite) {
            getProductDetail(product)
            getProductDescription(product)
        }

    }

    private fun getProductDetail(product: Product) {
        viewModelScope.launch {
            try {
                detailRepository.getProductDetail(product.meliId)?.let {
                    productDetail = it
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun getProductDescription(product: Product) {
        viewModelScope.launch {
            try {
                detailRepository.getProductDescription(product.meliId)?.let {
                    productDescription = it
                    description.set(it.text)
                    updateProduct(it)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun updateProduct(description: ProductDescription) {
        val productTmp = productField.get()
        productField.set(
            productTmp?.copy(
                description = description.text
            )
        )
    }

    fun toggleFavorites() {
        val state = !(isFavorite.get() ?: false)

        if (state) saveToFavorites()
        else removeFromFavorites()

        isFavorite.set(state)
    }

    fun openLink() {
        productField.get()?.let {
            _eventLiveData.postValue(DashBoardEvents.OpenURL(it.productUrl))
        }
    }

    private fun saveToFavorites() {
        productField.get()?.let {
            viewModelScope.launch {
                favoritesRepository.saveToFavorites(it)
            }
        }
    }

    private fun removeFromFavorites() {
        productField.get()?.let {
            viewModelScope.launch {
                favoritesRepository.removeFromFavorites(it)
            }
        }
    }

}
