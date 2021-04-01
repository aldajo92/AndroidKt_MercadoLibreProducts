package com.projects.aldajo92.mercadolibreproducts

import com.projects.aldajo92.mercadolibreproducts.framework.network.MeliProductService
import com.projects.aldajo92.mercadolibreproducts.data.MeliProductsRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.data.MeliProductListEntityMapper
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
//        stubMeliProductServiceGetMeliProducts(Single.just(MeliProductFactory.makeMeliProductResponse()))
//        val testObserver = meliProductRemoteImpl.getMeliProducts().test()
//        testObserver.assertComplete()
    }
//
    @Test
    fun getMeliProductsReturnsData() {
//        val meliProductResponse = MeliProductFactory.makeMeliProductResponse()
//        stubMeliProductServiceGetMeliProducts(Single.just(meliProductResponse))
//        val meliProductEntities = mutableListOf<MeliProductEntity>()
//        meliProductResponse.team.forEach {
//            meliProductEntities.add(entityMapper.mapFromRemote(it))
//        }
//
//        val testObserver = meliProductRemoteImpl.getMeliProducts().test()
//        testObserver.assertValue(meliProductEntities)
    }
//    //</editor-fold>
//
//    private fun stubMeliProductServiceGetMeliProducts(single: Single<MeliProductService.MeliProductResponse>) {
//        whenever(meliProductService.getProducts())
//            .thenReturn(single)
//    }
}