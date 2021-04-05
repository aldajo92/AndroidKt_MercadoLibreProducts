package com.projects.aldajo92.mercadolibreproducts.presentation.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.projects.aldajo92.mercadolibreproducts.domain.Product

@BindingAdapter("formatPrice")
fun valuePrice(textView: TextView, value: Int) {
    textView.text = value.formatPrice()
}

@BindingAdapter("formatProductPrice")
fun productPrice(textView: TextView, product: Product) {
    textView.text = product.getFormattedPrice()
}