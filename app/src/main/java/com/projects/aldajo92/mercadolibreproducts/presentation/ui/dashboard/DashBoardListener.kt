package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.DashBoardItem

interface DashBoardListener<T> {

    fun onClickItem(item: DashBoardItem<T>)

}