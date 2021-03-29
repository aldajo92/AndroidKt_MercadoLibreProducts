package com.projects.aldajo92.mercadolibreproducts.di

import com.projects.aldajo92.mercadolibreproducts.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun  contributeMainActivity(): MainActivity
}