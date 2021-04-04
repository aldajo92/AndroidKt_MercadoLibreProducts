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
import timber.log.Timber
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository<ProductDetail, ProductDescription>,
    private val favoritesRepository: FavoritesRepository<Product>
) : ViewModel() {

    lateinit var product: Product
    private lateinit var productDetail: ProductDetail
    private lateinit var productDescription: ProductDescription

    val description = ObservableField("")

    val priceFormat = ObservableField("")

    fun getProductDetail() {
        priceFormat.set("${product.currency} $${product.price}")

        viewModelScope.launch {
            detailRepository.getProductDetail(product.meliId)?.let {
                productDetail = it
            }
        }

        viewModelScope.launch {
            detailRepository.getProductDescription(product.meliId)?.let {
                productDescription = it
                description.set(it.text)
                Timber.i(it.text)
            }
        }
    }


    fun saveToFavorites() {
        Timber.i("Saved to favorites")
        // TODO: Not finished yet
//        viewModelScope.launch {
//            favoritesRepository.saveToFavorites(product)
//        }
    }

}
