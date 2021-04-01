package com.projects.aldajo92.mercadolibreproducts.data.repository

import com.projects.aldajo92.mercadolibreproducts.domain.MeliProduct

interface MeliFavoriteProductsRepository {

    suspend fun getMeliFavoriteProducts() : List<MeliProduct>

    suspend fun saveMeliFavoriteProduct(meliProduct: MeliProduct)

    suspend fun removeMeliFavoriteProduct(meliProduct: MeliProduct)

}