package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard.adapter

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.projects.aldajo92.mercadolibreproducts.databinding.ItemDashboardBinding
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.GenericItem
import com.projects.aldajo92.mercadolibreproducts.presentation.utils.formatMeliImgUrl

data class DashBoardItem(
    val product: Product,
    val layout: Int,
    val modelId: Int
) : GenericItem<Product>(product, layout, modelId) {

    var binding: ItemDashboardBinding? = null

    override fun bind(binding: ViewDataBinding, listener: DashBoardListener<Product>) {
        this.binding = binding as ItemDashboardBinding

        this.binding?.apply {
            setVariable(variableId, data)
            viewCover.setOnClickListener {
                listener.onClickItem(this@DashBoardItem)
            }

            if (product.imgId.isNotBlank()) {
                Glide.with(binding.root.context)
                    .load(product.formatMeliImgUrl())
                    .into(binding.imageViewPicture)
            }
        }
    }

}
