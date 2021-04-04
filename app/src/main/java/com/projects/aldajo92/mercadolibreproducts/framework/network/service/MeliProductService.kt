package com.projects.aldajo92.mercadolibreproducts.framework.network.service

import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDescriptionResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDetailResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface MeliProductService {

    @GET("sites/MCO/search")
    suspend fun getProductsFromSearch(
        @Query("q") word: String,
        @Query("offset") offset: Int = 0
    ): SearchResponse

    @GET("items/{id}")
    suspend fun getProductDetail(@Path("id") id: String): ProductDetailResponse

    @GET("items/{id}/description")
    suspend fun getProductDescription(@Path("id") id: String): ProductDescriptionResponse

}
