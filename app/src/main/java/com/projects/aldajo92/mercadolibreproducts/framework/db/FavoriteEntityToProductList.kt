package com.projects.aldajo92.mercadolibreproducts.framework.db

import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityListMapper
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.framework.db.entities.FavoriteProductEntity
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.ProductResponse
import javax.inject.Inject

/**
 * Map a [ProductResponse] to and from a [Product] instance when data is moving between
 * this later and the Data layer
 */
class FavoriteEntityToProductList @Inject constructor() :
    EntityListMapper<FavoriteProductEntity, Product> {

    /**
     * Map an instance of a [ProductResponse] to a [Product] model
     */
    override fun map(inputValue: List<FavoriteProductEntity>): List<Product> {
        return inputValue.map { product ->
            Product(
                product.meliId,
                product.title,
                product.price,
                false,
                "",
                product.imgId,
                product.productUrl,
                product.currency,
                product.description
            )
        }
    }
}
