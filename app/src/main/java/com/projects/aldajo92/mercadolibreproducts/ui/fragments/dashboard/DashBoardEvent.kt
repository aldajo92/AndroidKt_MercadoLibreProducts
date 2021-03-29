package com.projects.aldajo92.mercadolibreproducts.ui.fragments.dashboard

import com.projects.aldajo92.mercadolibreproducts.models.MeliProduct

sealed class DashBoardEvent {
    class ErrorMessage(val message: String) : DashBoardEvent()
    class ProductsSuccess(val products: List<MeliProduct>) : DashBoardEvent()
}