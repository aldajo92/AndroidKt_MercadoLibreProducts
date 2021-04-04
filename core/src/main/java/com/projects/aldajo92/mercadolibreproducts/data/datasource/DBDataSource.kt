package com.projects.aldajo92.mercadolibreproducts.data.datasource

interface DBDataSource<T> {
    suspend fun getStoredData(): List<T>

    suspend fun getById(id: String): T

    suspend fun storeData(data: T)

    suspend fun removeById(id: String)

    suspend fun removeAll()
}
