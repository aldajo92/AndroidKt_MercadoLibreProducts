package com.projects.aldajo92.mercadolibreproducts.data.mapper

interface EntityMapper<in I, out O> {

    fun map(inputValue: I): O

}