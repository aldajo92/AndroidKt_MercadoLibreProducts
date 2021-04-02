package com.projects.aldajo92.mercadolibreproducts.data.repository

import com.projects.aldajo92.mercadolibreproducts.domain.ProductDetail

class ProductDetailRepository(
) {

    suspend fun getProductDetail(id: String): ProductDetail {
        return ProductDetail("", 0, false)
    }

}