package com.projects.aldajo92.mercadolibreproducts.data.repository.detail

interface DetailRepository<T> {

    suspend fun getProductDetail(id: String): T

}
