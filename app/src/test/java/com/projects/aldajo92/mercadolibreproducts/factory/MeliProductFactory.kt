package com.projects.aldajo92.mercadolibreproducts.factory

import com.projects.aldajo92.mercadolibreproducts.factory.DataFactory.Factory.randomInt
import com.projects.aldajo92.mercadolibreproducts.factory.DataFactory.Factory.randomUuid
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.MeliProductResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.MeliSearchResult

/**
 * Factory class for MeliProduct related instances
 */
class MeliProductFactory {

    companion object Factory {

        fun makeMeliProductResponse(): MeliSearchResult {
            val meliProductResponse = MeliSearchResult()
//            meliProductResponse.productList = makeMeliProductModelList(5)
            return meliProductResponse
        }

        fun makeMeliProductModelList(count: Int): List<MeliProductResponse> {
            val meliProductEntities = mutableListOf<MeliProductResponse>()
            repeat(count) {
                meliProductEntities.add(makeMeliProductModel())
            }
            return meliProductEntities
        }

        fun makeMeliProductModel(): MeliProductResponse {
            return MeliProductResponse(randomUuid(), randomUuid(), randomInt(), "USD", randomInt())
        }

    }

}