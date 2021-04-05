package com.projects.aldajo92.mercadolibreproducts.di.modules.repository

import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiDetailDataSource
import com.projects.aldajo92.mercadolibreproducts.data.datasource.ApiSearchDataSource
import com.projects.aldajo92.mercadolibreproducts.data.datasource.CountriesDataSource
import com.projects.aldajo92.mercadolibreproducts.data.datasource.DBDataSource
import com.projects.aldajo92.mercadolibreproducts.data.repository.country.CountryRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.country.CountryRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.data.repository.detail.DetailRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.detail.DetailRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.data.repository.favorites.FavoritesRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.favorites.FavoritesRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.data.repository.search.SearchRepository
import com.projects.aldajo92.mercadolibreproducts.data.repository.search.SearchRepositoryImpl
import com.projects.aldajo92.mercadolibreproducts.domain.Country
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDescription
import com.projects.aldajo92.mercadolibreproducts.domain.ProductDetail
import com.projects.aldajo92.mercadolibreproducts.framework.db.FavoriteEntityToProductList
import com.projects.aldajo92.mercadolibreproducts.framework.db.MeliFavoriteDataSource
import com.projects.aldajo92.mercadolibreproducts.framework.db.ProductToEntity
import com.projects.aldajo92.mercadolibreproducts.framework.db.dao.FavoriteProductsDao
import com.projects.aldajo92.mercadolibreproducts.framework.db.entities.FavoriteProductEntity
import com.projects.aldajo92.mercadolibreproducts.framework.network.CountryResponseMapper
import com.projects.aldajo92.mercadolibreproducts.framework.network.ProductDescriptionResponseToProductDescription
import com.projects.aldajo92.mercadolibreproducts.framework.network.ProductDetailResponseToProductDetail
import com.projects.aldajo92.mercadolibreproducts.framework.network.ProductResponseToProductList
import com.projects.aldajo92.mercadolibreproducts.framework.network.datasources.CountriesDataSourceImpl
import com.projects.aldajo92.mercadolibreproducts.framework.network.datasources.MeliDetailDataSourceImpl
import com.projects.aldajo92.mercadolibreproducts.framework.network.datasources.MeliSearchDataSourceImpl
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.CountryResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDescriptionResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.detail.ProductDetailResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.models.search.ProductResponse
import com.projects.aldajo92.mercadolibreproducts.framework.network.service.MeliApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    internal fun provideDBProductDataSource(favoriteProductsDao: FavoriteProductsDao): DBDataSource<FavoriteProductEntity> {
        return MeliFavoriteDataSource(favoriteProductsDao)
    }

    @Provides
    internal fun provideCountriesDataSource(meliApiService: MeliApiService): CountriesDataSource<CountryResponse> {
        return CountriesDataSourceImpl(meliApiService)
    }

    @Provides
    internal fun provideApiProductDataSource(meliApiService: MeliApiService): ApiSearchDataSource<ProductResponse> {
        return MeliSearchDataSourceImpl(meliApiService)
    }

    @Provides
    internal fun provideApiProductDetailDataSource(
        meliApiService: MeliApiService
    ): ApiDetailDataSource<ProductDetailResponse, ProductDescriptionResponse> {
        return MeliDetailDataSourceImpl(meliApiService)
    }

    @Provides
    @Singleton
    internal fun provideCountryRepository(
        countryDataSource: CountriesDataSource<CountryResponse>,
        mapper: CountryResponseMapper
    ): CountryRepository<Country> {
        return CountryRepositoryImpl(countryDataSource, mapper)
    }

    @Provides
    internal fun provideSearchProductsRepository(
        apiSearch: ApiSearchDataSource<ProductResponse>,
        mapper: ProductResponseToProductList
    ): SearchRepository<Product> {
        return SearchRepositoryImpl(apiSearch, mapper)
    }

    @Provides
    internal fun provideDetailRepository(
        apiSearch: ApiDetailDataSource<ProductDetailResponse, ProductDescriptionResponse>,
        mapperDetail: ProductDetailResponseToProductDetail,
        mapperDescription: ProductDescriptionResponseToProductDescription
    ): DetailRepository<ProductDetail, ProductDescription> {
        return DetailRepositoryImpl(apiSearch, mapperDetail, mapperDescription)
    }

    @Provides
    internal fun provideFavoriteRepository(
        dbDataSource: DBDataSource<FavoriteProductEntity>,
        mapper: FavoriteEntityToProductList,
        invertMapper: ProductToEntity
    ): FavoritesRepository<Product> {
        return FavoritesRepositoryImpl(dbDataSource, mapper, invertMapper)
    }
}
