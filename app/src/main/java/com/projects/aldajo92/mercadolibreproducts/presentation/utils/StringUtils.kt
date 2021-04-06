package com.projects.aldajo92.mercadolibreproducts.presentation.utils

import com.projects.aldajo92.mercadolibreproducts.domain.Product
import timber.log.Timber
import java.text.NumberFormat

fun Int.formatPrice(): String {
    return "$${this}"
}

fun Int.formatPrice(currency: String): String {
    return "$currency $${this}"
}

fun Product.getFormattedPrice(): String {
    val format = NumberFormat.getCurrencyInstance()
        .apply {
            minimumFractionDigits = 0
            maximumFractionDigits = 2
        }

    return try {
        "${this.currency} ${format.format(this.price)}"
    } catch (e: IllegalArgumentException) {
        Timber.i("productId:${this.meliId} generates error: ${e.message}")
        "[Error]"
    }
}

fun Product.formatMeliImgUrl(): String {
    return "https://http2.mlstatic.com/D_${this.imgId}-O.jpg"
}
