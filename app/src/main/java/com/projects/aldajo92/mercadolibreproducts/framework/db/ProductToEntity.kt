package com.projects.aldajo92.mercadolibreproducts.framework.db

import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityMapper
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.framework.db.entities.FavoriteProductEntity
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.ProductResponse
import javax.inject.Inject

/**
 * Map a [ProductResponse] to and from a [Product] instance when data is moving between
 * this later and the Data layer
 */
class ProductToEntity @Inject constructor() :
    EntityMapper<Product, FavoriteProductEntity> {

    /**
     * Map an instance of a [ProductResponse] to a [Product] model
     */
    override fun map(inputValue: Product): FavoriteProductEntity {
        // TODO: fix this implementation
        return FavoriteProductEntity(
            inputValue.meliId,
            inputValue.title,
            inputValue.price,
            inputValue.currency,
            0,
            inputValue.description,
            inputValue.imgId,
            inputValue.productUrl
        )
    }
}
