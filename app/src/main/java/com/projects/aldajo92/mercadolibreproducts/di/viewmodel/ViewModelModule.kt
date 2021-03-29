package com.projects.aldajo92.mercadolibreproducts.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.aldajo92.mercadolibreproducts.viewModels.DashBoardViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashBoardViewModel::class)
    abstract fun dashboardViewModel(viewModel: DashBoardViewModel): ViewModel
}