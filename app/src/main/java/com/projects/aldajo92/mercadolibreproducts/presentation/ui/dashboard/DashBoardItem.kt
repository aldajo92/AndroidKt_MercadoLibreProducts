package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.projects.aldajo92.mercadolibreproducts.databinding.ItemDashboardBinding
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.GenericItem

data class DashBoardItem(
    val product: Product,
    val layout: Int,
    val modelId: Int
) : GenericItem<Product>(product, layout, modelId) {

    private var binding: ItemDashboardBinding? = null

    override fun bind(binding: ViewDataBinding, listener: DashBoardListener<Product>) {
        this.binding = binding as ItemDashboardBinding

        this.binding?.apply {
            setVariable(variableId, data)
            root.setOnClickListener {
                listener.onClickItem(this@DashBoardItem)
            }

            // TODO: Format URL with extension-function
            if(product.imgId.isNotBlank()){
                Glide.with(binding.root.context)
                    .load("https://http2.mlstatic.com/D_${product.imgId}-O.jpg")
                    .into(binding.imageViewPicture)
            }
        }
    }

}
