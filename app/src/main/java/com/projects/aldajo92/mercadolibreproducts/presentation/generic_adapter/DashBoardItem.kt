package com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard.DashBoardListener

data class DashBoardItem<T>(
    val data: T,
    @LayoutRes val layoutId: Int,
    val variableId: Int
) {

    private var binding : ViewDataBinding? = null
    private var listener : DashBoardListener<T>? = null

    fun bind(binding: ViewDataBinding, listener : DashBoardListener<T>) {
        this.binding = binding
        this.listener = listener

        binding.setVariable(variableId, data)
        binding.root.setOnClickListener {
            listener.onClickItem(this)
        }
    }
}
