package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import com.projects.aldajo92.mercadolibreproducts.domain.Product

sealed class DashBoardEvents {
    class ErrorMessage(val message: String) : DashBoardEvents()
    class ProductsSuccess(val productModels: List<Product>) : DashBoardEvents()
}