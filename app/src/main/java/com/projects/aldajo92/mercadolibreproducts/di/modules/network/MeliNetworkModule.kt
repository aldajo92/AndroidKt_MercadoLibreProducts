package com.projects.aldajo92.mercadolibreproducts.di.modules.network

import com.projects.aldajo92.mercadolibreproducts.MELI_BASE_URL
import com.projects.aldajo92.mercadolibreproducts.network.MeliApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class MeliNetworkModule {

    @Provides
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(MELI_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMovieApi(retrofit: Retrofit): MeliApiService {
        return retrofit.create(MeliApiService::class.java)
    }
}