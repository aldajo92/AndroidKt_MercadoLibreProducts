package com.projects.aldajo92.mercadolibreproducts.data.repository.detail

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiDetailDataSource
import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityMapper
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDescription
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDetail
import com.projects.aldajo92.mercadolibreproducts.models.ProductDescriptionDTO
import com.projects.aldajo92.mercadolibreproducts.models.ProductDetailDTO
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailRepositoryImplTest {

    @Mock
    lateinit var apiDetailDataSource: ApiDetailDataSource<ProductDetailDTO, ProductDescriptionDTO>

    private lateinit var detailRepositoryImpl: DetailRepositoryImpl<ProductDetailDTO, ProductDescriptionDTO>

    private val productDetailEntityMapper: EntityMapper<ProductDetailDTO, ProductDetail> by lazy {
        object : EntityMapper<ProductDetailDTO, ProductDetail> {
            override fun map(inputValue: ProductDetailDTO): ProductDetail {
                return ProductDetail(inputValue.title, inputValue.price)
            }
        }
    }

    private val productDescriptionEntityMapper: EntityMapper<ProductDescriptionDTO, ProductDescription> by lazy {
        object : EntityMapper<ProductDescriptionDTO, ProductDescription> {
            override fun map(inputValue: ProductDescriptionDTO): ProductDescription {
                return ProductDescription(inputValue.description, "", "")
            }
        }
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        detailRepositoryImpl = DetailRepositoryImpl(
            apiDetailDataSource,
            productDetailEntityMapper,
            productDescriptionEntityMapper
        )
    }

    @Test
    fun getProductsDetail_returnsComplete() {
        val id = "value"
        val productDetailDTO = ProductDetailDTO("1234", "title1", 10000)
        runBlocking {
            Mockito.`when`(apiDetailDataSource.getProductDetail(id)).thenReturn(productDetailDTO)
            val productDetail = detailRepositoryImpl.getProductDetail(id)
            assertEquals("title1", productDetail?.title)
            assertEquals(10000, productDetail?.price)
        }
    }

    @Test
    fun getProductsDetail_emptyId_returnsNull() {
        runBlocking {
            val productDetail = detailRepositoryImpl.getProductDetail("")
            assertNull("title1", productDetail)
        }
    }
}
