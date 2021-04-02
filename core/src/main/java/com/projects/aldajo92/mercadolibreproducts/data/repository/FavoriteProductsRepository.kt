package com.projects.aldajo92.mercadolibreproducts.data.repository

import com.projects.aldajo92.mercadolibreproducts.domain.Product

class FavoriteProductsRepository {

    suspend fun getMeliFavoriteProducts() : List<Product> {
        return emptyList()
    }

    suspend fun saveMeliFavoriteProduct(product: Product) {

    }

    suspend fun removeMeliFavoriteProduct(product: Product) {

    }

}