package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import com.projects.aldajo92.mercadolibreproducts.domain.Product

sealed class DashBoardEvent {
    class ErrorMessage(val message: String) : DashBoardEvent()
    class ProductsSuccess(val productModels: List<Product>) : DashBoardEvent()
}