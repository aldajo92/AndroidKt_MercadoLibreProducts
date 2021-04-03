package com.projects.aldajo92.mercadolibreproducts.datasource

import com.projects.aldajo92.mercadolibreproducts.framework.db.MeliFavoriteDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.db.dao.FavoriteProductsDao
import com.projects.aldajo92.mercadolibreproducts.framework.db.entities.FavoriteProductEntity
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
import org.mockito.kotlin.verify
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
class MeliFavoriteDataSourceTest {

    @Mock
    lateinit var favoriteProductsDao: FavoriteProductsDao

    private lateinit var meliFavoriteDataSource: MeliFavoriteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        meliFavoriteDataSource = MeliFavoriteDataSource(favoriteProductsDao)
    }

    @Test
    fun getStoredData() {
        val productFavorite = listOf(
            FavoriteProductEntity(Random.nextInt(), "Title1", 10000, "COP", 10),
            FavoriteProductEntity(Random.nextInt(), "Title2", 20000, "COP", 10)
        )

        runBlocking {
            `when`(favoriteProductsDao.getAllProducts()).thenReturn(productFavorite)
            assertEquals(2, meliFavoriteDataSource.getStoredData().size)
        }
    }

    @Test
    fun storeData() {
        runBlocking {
            val favoriteProduct = mock(FavoriteProductEntity::class.java)
            meliFavoriteDataSource.storeData(favoriteProduct)
            verify(favoriteProductsDao).addFavoriteProduct(favoriteProduct)
        }
    }
}
