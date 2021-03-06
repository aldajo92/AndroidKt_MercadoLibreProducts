package com.projects.aldajo92.mercadolibreproducts.presentation.ui

import com.projects.aldajo92.mercadolibreproducts.data.repository.country.CountryRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.search.SearchRepository
import com.projects.aldajo92.mercadolibreproducts.domain.Country
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard.DashBoardViewModel
import com.projects.aldajo92.mercadolibreproducts.test_utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyZeroInteractions


@RunWith(MockitoJUnitRunner::class)
class DashBoardViewModelTest {

    @Mock
    lateinit var countryRepository: CountryRepository<Country>

    @Mock
    lateinit var searchRepository: SearchRepository<Product>

    lateinit var dashboardViewModel: DashBoardViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        dashboardViewModel = DashBoardViewModel(countryRepository, searchRepository)
    }

    @Test
    fun performSearch_emptyKey_doesNothing() {
        dashboardViewModel.performFirstSearch("")
        verifyZeroInteractions(searchRepository)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun performSearch_notEmptyKey_getSearchFromRepository() {
        runBlocking {
            dashboardViewModel.performFirstSearch("1234")
            verify(searchRepository).getProductsFromSearch("1234", 0)
        }
    }

}
