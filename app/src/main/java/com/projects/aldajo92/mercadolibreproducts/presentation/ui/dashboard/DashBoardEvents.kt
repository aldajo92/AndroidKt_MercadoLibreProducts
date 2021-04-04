package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.events.DataEvent

sealed class DashBoardEvents<out T>(val content: T) : DataEvent<T>(content) {
    class ErrorMessage(message: String) : DashBoardEvents<String>(message)

    class ProductsSuccess(productModels: List<Product>) :
        DashBoardEvents<List<Product>>(productModels)

    class ProductsPaginationSuccess(productModels: List<Product>) :
        DashBoardEvents<List<Product>>(productModels)

}
