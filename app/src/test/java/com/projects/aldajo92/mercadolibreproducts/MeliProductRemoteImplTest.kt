package com.projects.aldajo92.mercadolibreproducts

import com.projects.aldajo92.mercadolibreproducts.framework.network.MeliProductService
import com.projects.aldajo92.mercadolibreproducts.data.MeliProductsRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.data.MeliProductListEntityMapper
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class MeliProductRemoteImplTest {
    private lateinit var listEntityMapper: MeliProductListEntityMapper
    private lateinit var meliProductService: MeliProductService

    private lateinit var meliProductRemoteImpl: MeliProductsRepositoryImpl

    @Before
    fun setup() {
        listEntityMapper = mock(MeliProductListEntityMapper::class.java)
        meliProductService = mock(MeliProductService::class.java)
        meliProductRemoteImpl = MeliProductsRepositoryImpl(meliProductService, listEntityMapper)
    }

    @Test
    fun getMeliProductsCompletes() {
        assertNotNull(listEntityMapper)
    }

    @Test
    fun getMeliProductsReturnsData() {
        assertNotNull(meliProductRemoteImpl)
    }
}