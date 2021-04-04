package com.projects.aldajo92.mercadolibreproducts.di.modules

import android.app.Application
import android.content.Context
import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiDetailDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.network.ProductResponseToProductList
import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiSearchDataSource
import com.projects.aldajo92.mercadolibreproducts.data.datasource.DBDataSource
import com.projects.aldajo92.mercadolibreproducts.data.repository.detail.DetailRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.detail.DetailRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.data.repository.favorites.FavoritesRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.favorites.FavoritesRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.data.repository.search.SearchRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.search.SearchProductApiRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDescription
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDetail
import com.projects.aldajo92.mercadolibreproducts.framework.db.FavoriteEntityToProductList
import com.projects.aldajo92.mercadolibreproducts.framework.db.MeliFavoriteDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.db.ProductToEntity
import com.projects.aldajo92.mercadolibreproducts.framework.db.dao.FavoriteProductsDao
import com.projects.aldajo92.mercadolibreproducts.framework.db.entities.FavoriteProductEntity
import com.projects.aldajo92.mercadolibreproducts.framework.network.ProductDescriptionResponseToProductDescription
import com.projects.aldajo92.mercadolibreproducts.framework.network.datasources.MeliDetailDataSourceImpl
import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliProductService
import com.projects.aldajo92.mercadolibreproducts.framework.network.datasources.MeliSearchDataSourceImpl
import com.projects.aldajo92.mercadolibreproducts.framework.network.ProductDetailResponseToProductDetail
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDescriptionResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDetailResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.ProductResponse
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun bindContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideDBProductDataSource(favoriteProductsDao: FavoriteProductsDao): DBDataSource<FavoriteProductEntity> {
        return MeliFavoriteDataSource(favoriteProductsDao)
    }

    @Provides
    @Singleton
    internal fun provideApiProductDataSource(meliProductService: MeliProductService): ApiSearchDataSource<ProductResponse> {
        return MeliSearchDataSourceImpl(meliProductService)
    }

    @Provides
    @Singleton
    internal fun provideApiProductDetailDataSource(
        meliProductService: MeliProductService
    ): ApiDetailDataSource<ProductDetailResponse, ProductDescriptionResponse> {
        return MeliDetailDataSourceImpl(meliProductService)
    }

    @Provides
    @Singleton
    internal fun provideSearchProductsRepository(
        apiSearch: ApiSearchDataSource<ProductResponse>,
        mapper: ProductResponseToProductList
    ): SearchRepository<Product> {
        return SearchProductApiRepositoryImpl(apiSearch, mapper)
    }

    @Provides
    @Singleton
    internal fun provideDetailRepository(
        apiSearch: ApiDetailDataSource<ProductDetailResponse, ProductDescriptionResponse>,
        mapperDetail: ProductDetailResponseToProductDetail,
        mapperDescription: ProductDescriptionResponseToProductDescription
    ): DetailRepository<ProductDetail, ProductDescription> {
        return DetailRepositoryImpl(apiSearch, mapperDetail, mapperDescription)
    }

    @Provides
    @Singleton
    internal fun provideFavoriteRepository(
        dbDataSource: DBDataSource<FavoriteProductEntity>,
        mapper: FavoriteEntityToProductList,
        invertMapper: ProductToEntity
    ): FavoritesRepository<Product> {
        return FavoritesRepositoryImpl(dbDataSource, mapper, invertMapper)
    }
}