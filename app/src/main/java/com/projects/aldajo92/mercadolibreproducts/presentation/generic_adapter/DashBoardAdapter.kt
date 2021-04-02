package com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard.DashBoardListener

class DashBoardAdapter<T>(private val dashBoardListener: DashBoardListener<T>) :
    RecyclerView.Adapter<BindingViewHolder>() {

    private val items = mutableListOf<DashBoardItem<T>>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).layoutId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        getItem(position).bind(holder.binding, dashBoardListener)
        holder.binding.executePendingBindings()
    }

    fun updateData(newItems: List<DashBoardItem<T>>) {
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): DashBoardItem<T> {
        return items[position]
    }
}
