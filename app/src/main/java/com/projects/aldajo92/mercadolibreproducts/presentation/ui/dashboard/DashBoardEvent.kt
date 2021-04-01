package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import com.projects.aldajo92.mercadolibreproducts.domain.MeliProduct

sealed class DashBoardEvent {
    class ErrorMessage(val message: String) : DashBoardEvent()
    class ProductsSuccess(val productModels: List<MeliProduct>) : DashBoardEvent()
}