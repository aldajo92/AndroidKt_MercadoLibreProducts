package com.projects.aldajo92.mercadolibreproducts.di.modules

import android.app.Application
import android.content.Context
import com.projects.aldajo92.mercadolibreproducts.data.ProductResponseToProductListMapper
import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiProductDataSource
import com.projects.aldajo92.mercadolibreproducts.data.datasource.DBProductDataSource
import com.projects.aldajo92.mercadolibreproducts.data.repository.SearchProductsRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.SearchProductsRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.framework.db.MeliDBProductDataSource
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
    internal fun provideDBProductDataSource(meliProductService: MeliProductService): DBProductDataSource<ProductEntity> {
        return MeliDBProductDataSource()
    }

    @Provides
    @Singleton
    internal fun provideApiProductDataSource(meliProductService: MeliProductService): ApiProductDataSource<ProductResponse> {
        return MeliSearchDataSource(meliProductService)
    }

    @Provides
    @Singleton
    internal fun provideSearchProductsRepository(
        api: ApiProductDataSource<ProductResponse>,
        database: DBProductDataSource<ProductEntity>,
        mapperProductResponseTo: ProductResponseToProductListMapper
    ): SearchProductsRepository<Product> {
        return SearchProductsRepositoryImpl(api, database, mapperProductResponseTo)
    }
}