package com.projects.aldajo92.mercadolibreproducts.framework.network.service

import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDetailResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface MeliProductService {

    @GET("sites/MCO/search")
    suspend fun getProductsFromSearch(@Query("q") word: String): SearchResponse

    @GET("items")
    suspend fun getProductDetail(@Query("ids") id: String): List<ProductDetailResponse>

}
