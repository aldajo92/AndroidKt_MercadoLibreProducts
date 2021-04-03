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
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
class MeliFavoriteDataSourceTest {

    lateinit var meliFavoriteDataSource: MeliFavoriteDataSource

    @Mock
    lateinit var favoriteProductsDao: FavoriteProductsDao

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

//    @Test
//    fun storeData() {
//
//    }
}
