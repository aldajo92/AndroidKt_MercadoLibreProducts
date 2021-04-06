package com.projects.aldajo92.mercadolibreproducts.data.repository.search

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiSearchDataSource
import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityListMapper
import com.projects.aldajo92.mercadolibreproducts.data.repository.country.CountryRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.country.CountryRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.domain.Country
import com.projects.aldajo92.mercadolibreproducts.models.ProductDTO
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.models.CountryDTO
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class SearchProductsRepositoryImplTest {

    @Mock
    lateinit var apiSearchDataSource: ApiSearchDataSource<ProductDTO>

    @Mock
    private lateinit var countryRepositoryImpl: CountryRepositoryImpl<CountryDTO>

    private lateinit var searchRepositoryImpl: SearchRepositoryImpl<ProductDTO>

    private val productListEntityMapper: EntityListMapper<ProductDTO, Product> by lazy {
        object : EntityListMapper<ProductDTO, Product> {
            override fun map(inputValue: List<ProductDTO>): List<Product> {
                return inputValue.map { productDTO ->
                    Product(productDTO.id, productDTO.title, productDTO.price)
                }
            }
        }
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        searchRepositoryImpl = SearchRepositoryImpl(
            countryRepositoryImpl,
            apiSearchDataSource,
            productListEntityMapper
        )
    }

    @Test
    fun getProductsFromSearch_returnsComplete() {
        val keyword = "value"
        val productDTO = listOf(
            ProductDTO("1234", "title1", 10000.0),
            ProductDTO("1235", "title2", 20000.0)
        )
        val country = Country(countryId = "COP")
        runBlocking {
            `when`(apiSearchDataSource.getProductsFromSearch("COP", keyword, 0))
                .thenReturn(productDTO)
            `when`(countryRepositoryImpl.getSelectedCountry())
                .thenReturn(country)

            val productsFromSearch = searchRepositoryImpl.getProductsFromSearch(keyword, 0)
            verify(apiSearchDataSource).getProductsFromSearch("COP", keyword, 0)
            verify(countryRepositoryImpl).getSelectedCountry()

            assertEquals(2, productsFromSearch?.size)
        }
    }

}
