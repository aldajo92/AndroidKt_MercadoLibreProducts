package com.projects.aldajo92.mercadolibreproducts.data.repository.favorites

import com.projects.aldajo92.mercadolibreproducts.data.datasource.DBDataSource
import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityListMapper
import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityMapper
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

    private lateinit var searchProductApiRepositoryImpl: FavoritesRepositoryImpl<ProductEntity>

    private val productListEntityMapper: EntityListMapper<ProductEntity, Product> by lazy {
        object : EntityListMapper<ProductEntity, Product> {
            override fun map(inputValue: List<ProductEntity>): List<Product> {
                return inputValue.map { productDTO ->
                    Product(
                        productDTO.id,
                        productDTO.title,
                        productDTO.price,
                        productDTO.isFavorite
                    )
                }
            }
        }
    }

    private val invertMapper: EntityMapper<Product, ProductEntity> by lazy {
        object : EntityMapper<Product, ProductEntity> {
            override fun map(inputValue: Product): ProductEntity {
                return ProductEntity(inputValue.meliId, inputValue.title, inputValue.price, false)
            }
        }
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        searchProductApiRepositoryImpl = FavoritesRepositoryImpl(
            dbDataSource,
            productListEntityMapper,
            invertMapper
        )
    }

    @Test
    fun getProductsFromSearch_returnsComplete() {
        val productEntity = listOf(
            ProductEntity("1233", "title1", 10000.0, false),
            ProductEntity("1234", "title2", 20000.0, true)
        )
        runBlocking {
            Mockito.`when`(dbDataSource.getStoredData()).thenReturn(productEntity)
            Assert.assertEquals(2, searchProductApiRepositoryImpl.getFavoritesList().size)
        }
    }

}
