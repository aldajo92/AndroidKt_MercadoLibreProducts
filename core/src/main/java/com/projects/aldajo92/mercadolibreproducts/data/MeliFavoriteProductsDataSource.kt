package com.projects.aldajo92.mercadolibreproducts.data

import com.projects.aldajo92.mercadolibreproducts.domain.MeliProduct

interface MeliFavoriteProductsDataSource {

    suspend fun add(meliProduct: MeliProduct)

    suspend fun readAll(): List<MeliProduct>

    suspend fun remove(meliProduct: MeliProduct)

}
