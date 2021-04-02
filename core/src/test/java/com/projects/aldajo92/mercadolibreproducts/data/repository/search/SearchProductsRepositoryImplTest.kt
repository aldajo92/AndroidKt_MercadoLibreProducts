package com.projects.aldajo92.mercadolibreproducts.data.repository.search

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiDataSource
import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityListMapper
import com.projects.aldajo92.mercadolibreproducts.models.ProductDTO
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchProductsRepositoryImplTest {

    @Mock
    lateinit var apiDataSource: ApiDataSource<ProductDTO>

    lateinit var searchProductApiRepositoryImpl: SearchProductApiRepositoryImpl<ProductDTO>

    private val productListEntityMapper: EntityListMapper<ProductDTO, Product> by lazy {
        object : EntityListMapper<ProductDTO, Product> {
            override fun map(inputValue: List<ProductDTO>): List<Product> {
                return inputValue.map { productDTO ->
                    Product(productDTO.title, productDTO.price)
                }
            }
        }
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        searchProductApiRepositoryImpl = SearchProductApiRepositoryImpl(
            apiDataSource,
            productListEntityMapper
        )
    }

    @Test
    fun getProductsFromSearch_returnsComplete() {
        val keyword = "value"
        val productDTO = listOf(
            ProductDTO("title1", 10000),
            ProductDTO("title2", 20000)
        )
        runBlocking {
            `when`(apiDataSource.getProductsFromSearch(keyword)).thenReturn(productDTO)
            assertEquals(2, searchProductApiRepositoryImpl.getProductsFromSearch(keyword).size)
        }
    }

}