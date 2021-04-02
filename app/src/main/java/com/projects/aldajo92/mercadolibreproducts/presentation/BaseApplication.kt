package com.projects.aldajo92.mercadolibreproducts.presentation

import com.projects.aldajo92.mercadolibreproducts.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        Timber.plant(Timber.DebugTree())
        return DaggerAppComponent.builder().application(this).build()
    }
}
