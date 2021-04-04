package com.projects.aldajo92.mercadolibreproducts.data.repository.search

/**
 * Interface defining methods for the caching of MeliProducts. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface SearchRepository<T> {

    /**
     * Retrieve a list of Meli products, from the cache
     */
    suspend fun getProductsFromSearch(keywords: String, offset: Int): List<T>?

}
