package com.projects.aldajo92.mercadolibreproducts.framework.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.projects.aldajo92.mercadolibreproducts.framework.db.entities.FavoriteProductEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MeliFavoritesDatabaseTest {

    private lateinit var context: Context

    private val database by lazy {
        Room.inMemoryDatabaseBuilder(context, MeliFavoritesDatabase::class.java).build()
    }

    private val favoriteProductDao by lazy {
        database.favoriteProductDao()
    }

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun writeFavoriteProductEntityAndRead() {
        val meliId = "1232"
        val favoriteProduct = FavoriteProductEntity(
            meliId,
            "Product X",
            10000,
            "COP",
            10
        )
        runBlocking {
            favoriteProductDao.addFavoriteProduct(favoriteProduct)

            val productFromDB = favoriteProductDao.getFavoriteProduct(meliId)
            assertEquals(productFromDB.title, favoriteProduct.title)
            assertEquals(productFromDB.price, favoriteProduct.price)
            assertEquals(productFromDB.stock, favoriteProduct.stock)

            favoriteProductDao.deleteAll()
        }
    }

    @Test
    fun getAllFavoriteProducts() {
        val favoriteProduct =
            listOf(
                FavoriteProductEntity(
                    "1234",
                    "Product X",
                    10000,
                    "COP",
                    5
                ),
                FavoriteProductEntity(
                    "1235",
                    "Product X",
                    20000,
                    "COP",
                    10
                )
            )
        runBlocking {
            favoriteProductDao.addFavoriteProduct(favoriteProduct[0])
            favoriteProductDao.addFavoriteProduct(favoriteProduct[1])

            val favoritesProducts = favoriteProductDao.getAllProducts()
            assertEquals(2, favoritesProducts.size)

            favoriteProductDao.deleteAll()
        }
    }

    @Test
    fun getFavoriteProductsAndDeleteOne() {
        val favoriteProduct =
            listOf(
                FavoriteProductEntity(
                    "1234",
                    "Product X",
                    10000,
                    "COP",
                    5
                ),
                FavoriteProductEntity(
                    "1235",
                    "Product X",
                    20000,
                    "COP",
                    10
                )
            )
        runBlocking {
            favoriteProductDao.addFavoriteProduct(favoriteProduct[0])
            favoriteProductDao.addFavoriteProduct(favoriteProduct[1])

            favoriteProductDao.deleteById("1234")

            val favoritesProducts = favoriteProductDao.getAllProducts()
            assertEquals(1, favoritesProducts.size)

            favoriteProductDao.deleteAll()
        }
    }

}
