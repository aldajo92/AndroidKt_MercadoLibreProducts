package com.projects.aldajo92.mercadolibreproducts.framework.network

import com.projects.aldajo92.mercadolibreproducts.data.mapper.EntityMapper
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDescription
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDetail
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDescriptionResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDetailResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.ProductResponse
import javax.inject.Inject

/**
 * Map a [ProductDetailResponse] to and from a [ProductDetail] instance when data is moving between
 * this later and the Data layer
 */
class ProductDescriptionResponseToProductDescription @Inject constructor() :
    EntityMapper<ProductDescriptionResponse, ProductDescription> {

    /**
     * Map an instance of a [ProductResponse] to a [Product] model
     */
    override fun map(inputValue: ProductDescriptionResponse): ProductDescription {
        return ProductDescription(
            inputValue.description,
            inputValue.last_updated,
            inputValue.date_created
        )
    }
}
