package com.projects.aldajo92.mercadolibreproducts.presentation.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.projects.aldajo92.mercadolibreproducts.R
import com.projects.aldajo92.mercadolibreproducts.domain.Product

@BindingAdapter("formatPrice")
fun valuePrice(textView: TextView, value: Int) {
    textView.text = value.formatPrice()
}

@BindingAdapter("formatProductPrice")
fun productPrice(textView: TextView, product: Product) {
    textView.text = product.getFormattedPrice()
}

@BindingAdapter("favoriteMarkState")
fun favoriteMarkState(imageView: ImageView, marked: Boolean) {
    imageView.setImageDrawable(
        ContextCompat.getDrawable(
            imageView.context, if (marked) R.drawable.ic_star_marked else R.drawable.ic_star
        )
    )
}
