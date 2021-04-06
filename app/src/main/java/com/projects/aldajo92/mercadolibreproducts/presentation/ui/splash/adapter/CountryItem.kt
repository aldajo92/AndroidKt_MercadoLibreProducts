package com.projects.aldajo92.mercadolibreproducts.presentation.ui.splash.adapter

import androidx.databinding.ViewDataBinding
import com.projects.aldajo92.mercadolibreproducts.databinding.ItemCountryBinding
import com.projects.aldajo92.mercadolibreproducts.domain.Country
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.GenericItem
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.ItemListener

data class CountryItem(
    val country: Country,
    val layout: Int,
    val modelId: Int
) : GenericItem<Country>(country, layout, modelId) {

    var binding: ItemCountryBinding? = null

    override fun bind(binding: ViewDataBinding, listener: ItemListener<Country>) {
        this.binding = binding as ItemCountryBinding

        this.binding?.apply {
            setVariable(variableId, data)
            viewCover.setOnClickListener {
                listener.onClickItem(this@CountryItem)
            }
        }
    }

}
