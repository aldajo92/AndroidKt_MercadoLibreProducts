package com.projects.aldajo92.mercadolibreproducts.di.modules.ui

import com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard.DashBoardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): DashBoardFragment
}