package com.projects.aldajo92.mercadolibreproducts.data

import com.projects.aldajo92.mercadolibreproducts.data.repository.MeliProductsRepository
import com.projects.aldajo92.mercadolibreproducts.domain.MeliProduct
import com.projects.aldajo92.mercadolibreproducts.framework.network.MeliProductService
import javax.inject.Inject

class MeliProductsRepositoryImpl @Inject constructor(
    private val meliProductService: MeliProductService,
    private val meliProductListEntityMapper: MeliProductListEntityMapper
) : MeliProductsRepository {
    override suspend fun getProductsFromSearch(keywords: String): List<MeliProduct> {
        val value = meliProductService.getProductsFromSearch(keywords)
        return meliProductListEntityMapper.map(value.productList)
    }

}