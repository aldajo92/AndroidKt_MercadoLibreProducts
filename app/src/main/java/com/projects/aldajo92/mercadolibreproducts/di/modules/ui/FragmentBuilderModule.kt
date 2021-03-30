package com.projects.aldajo92.mercadolibreproducts.di.modules.ui

import com.projects.aldajo92.mercadolibreproducts.ui.fragments.dashboard.DashBoardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): DashBoardFragment
}