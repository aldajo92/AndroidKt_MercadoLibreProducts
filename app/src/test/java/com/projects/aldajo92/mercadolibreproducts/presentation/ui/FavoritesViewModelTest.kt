package com.projects.aldajo92.mercadolibreproducts.presentation.ui

import com.projects.aldajo92.mercadolibreproducts.data.repository.favorites.FavoritesRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.favorites.FavoritesViewModel
import com.projects.aldajo92.mercadolibreproducts.test_utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify


@RunWith(MockitoJUnitRunner::class)
class FavoritesViewModelTest {

    @Mock
    lateinit var favoritesRepository: FavoritesRepository<Product>

    lateinit var favoriteViewModel: FavoritesViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        favoriteViewModel = FavoritesViewModel(favoritesRepository)
    }

    @Test
    fun getFavoritesProducts() {
        favoriteViewModel.getFavoriteProducts()

        runBlocking {
            verify(favoritesRepository).getFavoritesList()
        }
    }

}
