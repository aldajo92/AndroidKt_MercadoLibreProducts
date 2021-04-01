package com.projects.aldajo92.mercadolibreproducts.di.modules

import android.app.Application
import android.content.Context
import com.projects.aldajo92.mercadolibreproducts.data.repository.MeliProductsRepository
import com.projects.aldajo92.mercadolibreproducts.framework.network.MeliProductService
import com.projects.aldajo92.mercadolibreproducts.data.MeliProductsRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.data.MeliProductListEntityMapper
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
    internal fun provideMeliProductsRemoteImpl(service: MeliProductService,
                                       factory: MeliProductListEntityMapper
    ): MeliProductsRepository {
        return MeliProductsRepositoryImpl(service, factory)
    }
}