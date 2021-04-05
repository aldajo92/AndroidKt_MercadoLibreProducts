package com.projects.aldajo92.mercadolibreproducts.presentation.utils

import com.projects.aldajo92.mercadolibreproducts.domain.Product

fun Int.formatPrice(): String {
    return "$${this}"
}

fun Int.formatPrice(currency: String): String {
    return "$currency $${this}"
}

fun Product.getFormattedPrice(): String {
    return "${this.currency} $${this.price}"
}

fun String.formatMeliImgUrl(): String {
    return "https://http2.mlstatic.com/D_${this}-O.jpg"
}