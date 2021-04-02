package com.projects.aldajo92.mercadolibreproducts.data.repository.favorites

interface FavoritesRepository<T> {

    suspend fun getFavoritesList() : List<T>

    suspend fun saveToFavorites(product: T)

    suspend fun removeFromFavorites(product: T)

}
