package com.projects.aldajo92.mercadolibreproducts.di.modules.db

import android.content.Context
import androidx.room.Room
import com.projects.aldajo92.mercadolibreproducts.framework.db.MeliFavoritesDatabase
import com.projects.aldajo92.mercadolibreproducts.framework.db.dao.FavoriteProductsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Provides
    @Singleton
    internal fun provideMeliFavoritesDatabase(context: Context): MeliFavoritesDatabase {
        return Room.databaseBuilder(context, MeliFavoritesDatabase::class.java, "favorite_products")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    internal fun provideFavoriteDao(database: MeliFavoritesDatabase): FavoriteProductsDao {
        return database.favoriteProductDao()
    }

//    @Provides
//    @Singleton
//    internal fun provideFavoriteDao(): FavoriteProductsDao {
//        return object : FavoriteProductsDao {
//            override suspend fun addFavoriteProduct(product: FavoriteProductEntity) {
//
//            }
//
//            override suspend fun getAllProducts(): List<FavoriteProductEntity> {
//                return emptyList()
//            }
//
//        }
//    }

}