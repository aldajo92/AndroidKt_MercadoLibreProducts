package com.projects.aldajo92.mercadolibreproducts.di.modules

import com.projects.aldajo92.mercadolibreproducts.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun  contributeMainActivity(): MainActivity
}