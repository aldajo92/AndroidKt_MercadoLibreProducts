package com.projects.aldajo92.mercadolibreproducts.datasource

import com.projects.aldajo92.mercadolibreproducts.framework.network.datasources.MeliDetailDataSourceImpl
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDescriptionResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDetailResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
class MeliDetailDataSourceTest {

    @Mock
    lateinit var meliApiService: MeliApiService

    lateinit var meliDetailDataSourceImpl: MeliDetailDataSourceImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        meliDetailDataSourceImpl = MeliDetailDataSourceImpl(meliApiService)
    }

    @Test
    fun getProductsDetail_emptyId() {
        runBlocking {
            assertNull(meliDetailDataSourceImpl.getProductDetail(""))
        }
    }

    @Test
    fun getProductsDescription_emptyId() {
        runBlocking {
            assertNull(meliDetailDataSourceImpl.getProductDetailDescription(""))
        }
    }

    @Test
    fun getProductsDetail_notEmptyId() {
        val keyword = "keyword"
        val productDetailResponse = ProductDetailResponse(
            Random.toString(),
            10,
            10,
            0,
            "new"
        )
        runBlocking {
            `when`(meliApiService.getProductDetail(keyword)).thenReturn(productDetailResponse)
            val productDetail = meliDetailDataSourceImpl.getProductDetail(keyword)
            assertEquals(10, productDetail?.initial_quantity)
            assertEquals(10, productDetail?.available_quantity)
            assertEquals(0, productDetail?.sold_quantity)
            assertEquals("new", productDetail?.condition)
        }
    }

    @Test
    fun getProductsDescription_notEmptyId() {
        val keyword = "keyword"
        val productDescriptionResponse = ProductDescriptionResponse(
            "description",
            "last_updated",
            "date_created"
        )
        runBlocking {
            `when`(meliApiService.getProductDescription(keyword)).thenReturn(
                productDescriptionResponse
            )
            val productDescription = meliDetailDataSourceImpl.getProductDetailDescription(keyword)
            assertEquals("description", productDescription?.description)
            assertEquals("last_updated", productDescription?.last_updated)
            assertEquals("date_created", productDescription?.date_created)
        }
    }
}
