package com.projects.aldajo92.mercadolibreproducts.network

import com.projects.aldajo92.mercadolibreproducts.models.MeliResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.mercadolibre.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MeliApiService {
    @GET("sites/MCO/search?q=Motorola%20G6")
    suspend fun getProducts(): MeliResult
}

object MeliApi {
    val retrofitService : MeliApiService by lazy { retrofit.create(MeliApiService::class.java) }
}