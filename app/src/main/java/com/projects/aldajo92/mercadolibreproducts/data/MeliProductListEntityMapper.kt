package com.projects.aldajo92.mercadolibreproducts.data

import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityListMapper
import com.projects.aldajo92.mercadolibreproducts.domain.MeliProduct
import com.projects.aldajo92.mercadolibreproducts.framework.db.MeliProductEntity
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.MeliProductResponse
import javax.inject.Inject

/**
 * Map a [MeliProductResponse] to and from a [MeliProductEntity] instance when data is moving between
 * this later and the Data layer
 */
open class MeliProductListEntityMapper @Inject constructor() :
    EntityListMapper<MeliProductResponse, MeliProduct> {

    /**
     * Map an instance of a [MeliProductResponse] to a [MeliProductEntity] model
     */
    override fun map(inputValue: List<MeliProductResponse>): List<MeliProduct> {
        return inputValue.map { product ->
            MeliProduct(product.title, product.price)
        }
    }
}