package com.projects.aldajo92.mercadolibreproducts.di.modules

import com.projects.aldajo92.mercadolibreproducts.ui.fragments.dashboard.DashboardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): DashboardFragment
}