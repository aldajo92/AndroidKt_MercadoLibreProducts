package com.projects.aldajo92.mercadolibreproducts.network

import com.projects.aldajo92.mercadolibreproducts.models.MeliResult
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface MeliApiService {
    @GET("sites/MCO/search?q=Motorola%20G6")
    suspend fun getProducts(): MeliResult
}