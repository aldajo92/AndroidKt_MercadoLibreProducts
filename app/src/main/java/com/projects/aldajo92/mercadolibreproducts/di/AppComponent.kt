package com.projects.aldajo92.mercadolibreproducts.di

import android.app.Application
import com.projects.aldajo92.mercadolibreproducts.BaseApplication
import com.projects.aldajo92.mercadolibreproducts.di.modules.ActivityBuilderModule
import com.projects.aldajo92.mercadolibreproducts.di.modules.AppModule
import com.projects.aldajo92.mercadolibreproducts.di.modules.FragmentBuilderModule
import com.projects.aldajo92.mercadolibreproducts.di.modules.network.MeliNetworkModule
import com.projects.aldajo92.mercadolibreproducts.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        ViewModelModule::class,
        MeliNetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}