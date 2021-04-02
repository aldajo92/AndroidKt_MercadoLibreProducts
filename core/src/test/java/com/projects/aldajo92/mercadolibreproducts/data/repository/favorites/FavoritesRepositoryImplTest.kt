package com.projects.aldajo92.mercadolibreproducts.data.repository.favorites

import com.projects.aldajo92.mercadolibreproducts.data.datasource.DBDataSource
import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityListMapper
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.models.ProductEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoritesRepositoryImplTest {

    @Mock
    lateinit var dbDataSource: DBDataSource<ProductEntity>

    lateinit var searchProductApiRepositoryImpl: FavoritesRepositoryImpl<ProductEntity>

    private val productListEntityMapper: EntityListMapper<ProductEntity, Product> by lazy {
        object : EntityListMapper<ProductEntity, Product> {
            override fun map(inputValue: List<ProductEntity>): List<Product> {
                return inputValue.map { productDTO ->
                    Product(productDTO.title, productDTO.price, productDTO.isFavorite)
                }
            }
        }
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        searchProductApiRepositoryImpl = FavoritesRepositoryImpl(
            dbDataSource,
            productListEntityMapper
        )
    }

    @Test
    fun getProductsFromSearch_returnsComplete() {
        val productEntity = listOf(
            ProductEntity("title1", 10000, false),
            ProductEntity("title2", 20000, true)
        )
        runBlocking {
            Mockito.`when`(dbDataSource.getStoredData()).thenReturn(productEntity)
            Assert.assertEquals(2, searchProductApiRepositoryImpl.getFavoritesList().size)
        }
    }

}
