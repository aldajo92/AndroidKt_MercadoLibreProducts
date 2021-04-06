package com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class GenericAdapter<T>(private val itemListener: ItemListener<T>) :
    RecyclerView.Adapter<BindingViewHolder>() {

    private val items = mutableListOf<GenericItem<T>>()

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
        getItem(position).bind(holder.binding, itemListener)
        holder.binding.executePendingBindings()
    }

    fun clearAndUpdateData(newItems: List<GenericItem<T>>) {
        this.items.clear()
        updateData(newItems)
    }

    fun updateData(newItems: List<GenericItem<T>>) {
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): GenericItem<T> {
        return items[position]
    }
}
