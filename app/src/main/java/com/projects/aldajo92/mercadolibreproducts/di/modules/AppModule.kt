package com.projects.aldajo92.mercadolibreproducts.di.modules

import android.app.Application
import android.content.Context
import com.projects.aldajo92.mercadolibreproducts.framework.network.ProductResponseToProductList
import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiDataSource
import com.projects.aldajo92.mercadolibreproducts.data.datasource.DBDataSource
import com.projects.aldajo92.mercadolibreproducts.data.repository.search.SearchRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.search.SearchProductApiRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.framework.db.MeliDBDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.db.entities.ProductEntity
import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliProductService
import com.projects.aldajo92.mercadolibreproducts.framework.network.MeliSearchDataSource
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

//    @Provides
//    @Singleton
//    internal fun provideMeliProductsRemoteImpl(service: ProductService,
//                                               factory: ProductListEntityMapper
//    ): SearchProductsRepository<Product> {
//        return SearchProductsRepositoryImpl<ProductResponse, ProductEntity>(service, factory)
//    }

    @Provides
    @Singleton
    internal fun provideDBProductDataSource(meliProductService: MeliProductService): DBDataSource<ProductEntity> {
        return MeliDBDataSource()
    }

    @Provides
    @Singleton
    internal fun provideApiProductDataSource(meliProductService: MeliProductService): ApiDataSource<ProductResponse> {
        return MeliSearchDataSource(meliProductService)
    }

    @Provides
    @Singleton
    internal fun provideSearchProductsRepository(
        api: ApiDataSource<ProductResponse>,
        mapperProductResponseTo: ProductResponseToProductList
    ): SearchRepository<Product> {
        return SearchProductApiRepositoryImpl(api, mapperProductResponseTo)
    }
}