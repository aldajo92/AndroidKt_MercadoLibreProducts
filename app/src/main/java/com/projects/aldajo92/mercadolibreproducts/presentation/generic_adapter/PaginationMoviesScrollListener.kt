package com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationMoviesScrollListener(
    private val layoutManager: GridLayoutManager,
    private val visibleThreshold: Int
) : RecyclerView.OnScrollListener() {

    var currentPage = 0
    private var isLoading = true
    private var previousTotalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        val totalItemCount = layoutManager.itemCount

        if (isLoading && totalItemCount > previousTotalItemCount) {
            isLoading = false
            previousTotalItemCount = totalItemCount
        }

        if (!isLoading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount)
            isLoading = true
        }
    }

    abstract fun onLoadMore(currentPage: Int, totalItemCount: Int)

    fun resetPagination() {
        currentPage = 0
        isLoading = true
        previousTotalItemCount = 0
    }
}