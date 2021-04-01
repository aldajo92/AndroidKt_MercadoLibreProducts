package com.projects.aldajo92.mercadolibreproducts.framework.network

import com.projects.aldajo92.mercadolibreproducts.framework.network.models.MeliSearchResult
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface MeliProductService {
    @GET("sites/MCO/search?q=Motorola%20G6")
    suspend fun getProducts(): MeliSearchResult

    @GET("sites/MCO/search")
    suspend fun getProductsFromSearch(@Query("q") word: String): MeliSearchResult
}