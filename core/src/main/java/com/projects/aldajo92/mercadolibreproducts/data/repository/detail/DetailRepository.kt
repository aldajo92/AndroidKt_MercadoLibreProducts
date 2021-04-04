package com.projects.aldajo92.mercadolibreproducts.data.repository.detail

interface DetailRepository<T, S> {

    suspend fun getProductDetail(id: String): T

    suspend fun getProductDescription(id: String) : S

}
