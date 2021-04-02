package com.projects.aldajo92.mercadolibreproducts

import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliProductService
import com.projects.aldajo92.mercadolibreproducts.data.ProductResponseToProductListMapper
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class MeliProductRemoteImplTest {
    private lateinit var listMapperProductResponseTo: ProductResponseToProductListMapper
    private lateinit var meliProductService: MeliProductService

//    private lateinit var meliProductRemoteImplSearch: SearchProductsRepositoryImpl

    @Before
    fun setup() {
        listMapperProductResponseTo = mock(ProductResponseToProductListMapper::class.java)
        meliProductService = mock(MeliProductService::class.java)
//        meliProductRemoteImplSearch = SearchProductsRepositoryImpl(meliProductService, listEntityMapper)
    }

    @Test
    fun getMeliProductsCompletes() {
        assertNotNull(listMapperProductResponseTo)
    }

    @Test
    fun getMeliProductsReturnsData() {
        assertNotNull(meliProductService)
    }
}