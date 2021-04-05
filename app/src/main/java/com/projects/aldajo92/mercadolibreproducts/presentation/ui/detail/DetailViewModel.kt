package com.projects.aldajo92.mercadolibreproducts.presentation.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.aldajo92.mercadolibreproducts.data.repository.detail.DetailRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.favorites.FavoritesRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDescription
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDetail
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository<ProductDetail, ProductDescription>,
    private val favoritesRepository: FavoritesRepository<Product>
) : ViewModel() {

    //    private var productModel: Product? = null
    private lateinit var productDetail: ProductDetail
    private lateinit var productDescription: ProductDescription

    val description = ObservableField("")

    val isFavorite = ObservableField(false)

    val productField = ObservableField<Product>()

    fun setupProductInformation(product: Product, isFavorite: Boolean) {
        this.productField.set(product)
        this.isFavorite.set(isFavorite)
//        this.productModel = product
//        priceFormat.set(product.getFormattedPrice())

        if (!isFavorite) {
            getProductDetail(product)
            getProductDescription(product)
        }

    }

    private fun getProductDetail(product: Product) {
        viewModelScope.launch {
            detailRepository.getProductDetail(product.meliId)?.let {
                productDetail = it
            }
        }
    }

    private fun getProductDescription(product: Product) {
        viewModelScope.launch {
            detailRepository.getProductDescription(product.meliId)?.let {
                productDescription = it
                description.set(it.text)

                updateProduct(it)
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
