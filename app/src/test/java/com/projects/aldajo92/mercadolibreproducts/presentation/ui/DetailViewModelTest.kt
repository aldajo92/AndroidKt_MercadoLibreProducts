package com.projects.aldajo92.mercadolibreproducts.presentation.ui

import com.projects.aldajo92.mercadolibreproducts.data.repository.detail.DetailRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.favorites.FavoritesRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDescription
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDetail
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.detail.DetailViewModel
import com.projects.aldajo92.mercadolibreproducts.test_utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @Mock
    lateinit var detailRepository: DetailRepository<ProductDetail, ProductDescription>

    @Mock
    lateinit var favoritesRepository: FavoritesRepository<Product>

    lateinit var detailViewModel: DetailViewModel

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        detailViewModel = DetailViewModel(detailRepository, favoritesRepository)
    }

    @Test
    fun getProductInformation_callsInformationFromRepository() {
        val mockProduct = Product(
            "1234",
            "title",
            2000,
            false,
            "url.com",
            "imageId123",
            "product.com",
            "COP"
        )
        runBlocking {
            detailViewModel.getProductInformation(mockProduct)
            verify(detailRepository).getProductDescription(mockProduct.meliId)
            verify(detailRepository).getProductDetail(mockProduct.meliId)
        }
    }

    //TODO: add other edge cases for testing
}