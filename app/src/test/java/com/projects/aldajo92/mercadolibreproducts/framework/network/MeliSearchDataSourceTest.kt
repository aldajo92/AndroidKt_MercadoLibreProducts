package com.projects.aldajo92.mercadolibreproducts.framework.network

import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.ProductResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
class MeliSearchDataSourceTest {

    @Mock
    lateinit var meliSearchDataSource: MeliSearchDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getProductsFromSearch() {
        val keyword = "keyword"
        val productEntity = listOf(
            ProductResponse(Random.toString(), "Title1", 10000, "COP", 10),
            ProductResponse(Random.toString(), "Title2", 20000, "COP", 10),
            ProductResponse(Random.toString(), "Title3", 30000, "COP", 10),
            ProductResponse(Random.toString(), "Title4", 40000, "COP", 10)
        )
        runBlocking {
            Mockito.`when`(meliSearchDataSource.getProductsFromSearch(keyword))
                .thenReturn(productEntity)
            Assert.assertEquals(4, meliSearchDataSource.getProductsFromSearch(keyword).size)
        }
    }
}