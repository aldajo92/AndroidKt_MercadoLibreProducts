package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard.adapter

import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.GenericItem

interface DashBoardListener<T> {

    fun onClickItem(item: GenericItem<T>)

}
