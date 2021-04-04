package com.projects.aldajo92.mercadolibreproducts.datasource

import com.projects.aldajo92.mercadolibreproducts.framework.network.datasources.MeliSearchDataSourceImpl
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.ProductResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.SearchResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliProductService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
class MeliSearchDataSourceTest {

    @Mock
    lateinit var meliProductService: MeliProductService

    lateinit var meliSearchDataSourceImpl: MeliSearchDataSourceImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        meliSearchDataSourceImpl = MeliSearchDataSourceImpl(meliProductService)
    }

    @Test
    fun getProductsFromSearch_empty() {
        val keyword = ""
        runBlocking {
            assertEquals(0, meliSearchDataSourceImpl.getProductsFromSearch(keyword, 0).size)
        }
    }

    @Test
    fun getProductsFromSearch_notEmpty() {
        val keyword = "keyword"
        val productEntity = listOf(
            ProductResponse(Random.toString(), "Title1", 10000, "COP", 10),
            ProductResponse(Random.toString(), "Title2", 20000, "COP", 10),
            ProductResponse(Random.toString(), "Title3", 30000, "COP", 10),
            ProductResponse(Random.toString(), "Title4", 40000, "COP", 10)
        )
        val responseMock = mock(SearchResponse::class.java)
        `when`(responseMock.productList).thenReturn(productEntity)
        runBlocking {
            `when`(meliProductService.getProductsFromSearch(keyword)).thenReturn(responseMock)
            assertEquals(4, meliSearchDataSourceImpl.getProductsFromSearch(keyword, 0).size)
        }
    }
}
