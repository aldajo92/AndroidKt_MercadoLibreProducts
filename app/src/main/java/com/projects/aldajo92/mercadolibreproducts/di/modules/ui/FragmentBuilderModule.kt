package com.projects.aldajo92.mercadolibreproducts.di.modules.ui

import com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard.DashBoardFragment
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.detail.DetailFragment
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.favorites.FavoritesFragment
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun bindDashBoardFragment(): DashBoardFragment

    @ContributesAndroidInjector
    abstract fun bindDetailFragment(): DetailFragment

    @ContributesAndroidInjector
    abstract fun bindFavoriteFragment(): FavoritesFragment
}