package com.projects.aldajo92.mercadolibreproducts.presentation.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.projects.aldajo92.mercadolibreproducts.BR
import com.projects.aldajo92.mercadolibreproducts.R
import com.projects.aldajo92.mercadolibreproducts.databinding.FragmentFavoritesBinding
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.events.DashBoardEvents
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.GenericAdapter
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.GenericItem
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.BaseFragment
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard.adapter.DashBoardItem
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard.adapter.DashBoardListener
import com.projects.aldajo92.mercadolibreproducts.presentation.utils.calculateBestSpanCount
import javax.inject.Inject

class FavoritesFragment : BaseFragment(), DashBoardListener<Product> {

    @Inject
    lateinit var viewModel: FavoritesViewModel

    private lateinit var binding: FragmentFavoritesBinding

    private lateinit var productAdapter: GenericAdapter<Product>

    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showBottomNavigation(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        productAdapter = GenericAdapter(this)

        gridLayoutManager = GridLayoutManager(
            activity,
            calculateBestSpanCount(
                requireActivity(),
                resources.getDimensionPixelSize(R.dimen.width_image_home)
            )
        )

        binding.recyclerViewProducts.apply {
            layoutManager = gridLayoutManager
            adapter = productAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteProducts()

        viewModel.responseLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is DashBoardEvents.ProductsSuccess -> handleResponse(it.getDataOnce())
                is DashBoardEvents.ErrorMessage -> showToastMessage(it.getDataOnce())
                else -> Unit
            }
        })
    }

    private fun handleResponse(productModels: List<Product>?) {
        productModels?.map {
            DashBoardItem(it, R.layout.item_dashboard, BR.model)
        }?.let {
            productAdapter.clearAndUpdateData(it)
        }
    }

    override fun onClickItem(item: GenericItem<Product>) {

    }

}
