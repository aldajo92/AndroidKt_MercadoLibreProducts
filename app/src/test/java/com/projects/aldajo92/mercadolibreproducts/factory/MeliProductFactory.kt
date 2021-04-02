package com.projects.aldajo92.mercadolibreproducts.factory

import com.projects.aldajo92.mercadolibreproducts.factory.DataFactory.Factory.randomInt
import com.projects.aldajo92.mercadolibreproducts.factory.DataFactory.Factory.randomUuid
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.ProductResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.SearchResponse

/**
 * Factory class for MeliProduct related instances
 */
class MeliProductFactory {

    companion object Factory {

        fun makeMeliProductResponse(): SearchResponse {
            val meliProductResponse = SearchResponse()
//            meliProductResponse.productList = makeMeliProductModelList(5)
            return meliProductResponse
        }

        fun makeMeliProductModelList(count: Int): List<ProductResponse> {
            val meliProductEntities = mutableListOf<ProductResponse>()
            repeat(count) {
                meliProductEntities.add(makeMeliProductModel())
            }
            return meliProductEntities
        }

        fun makeMeliProductModel(): ProductResponse {
            return ProductResponse(randomUuid(), randomUuid(), randomInt(), "USD", randomInt())
        }

    }

}