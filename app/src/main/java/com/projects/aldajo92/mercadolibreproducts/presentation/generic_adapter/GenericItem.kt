package com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

abstract class GenericItem<T>(
    val data: T,
    @LayoutRes val layoutId: Int,
    val variableId: Int
) {
    abstract fun bind(binding: ViewDataBinding, listener: ItemListener<T>)
}
