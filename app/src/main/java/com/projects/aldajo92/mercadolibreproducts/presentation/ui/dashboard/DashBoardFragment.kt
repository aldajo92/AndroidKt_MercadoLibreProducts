package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.projects.aldajo92.mercadolibreproducts.BR
import com.projects.aldajo92.mercadolibreproducts.R
import com.projects.aldajo92.mercadolibreproducts.databinding.FragmentDashboardBinding
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.events.DashBoardEvents
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.GenericAdapter
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.GenericItem
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.ItemListener
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.PaginationScrollListener
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.BaseFragment
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.COUNTRY_SELECTED_KEY
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard.adapter.DashBoardItem
import com.projects.aldajo92.mercadolibreproducts.presentation.utils.calculateBestSpanCount
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject


class DashBoardFragment : BaseFragment(), ItemListener<Product> {

    @Inject
    lateinit var viewModel: DashBoardViewModel

    private lateinit var binding: FragmentDashboardBinding

    private lateinit var productAdapter: GenericAdapter<Product>

    private lateinit var gridLayoutManager: GridLayoutManager

    private val args: DashBoardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showBottomNavigation(true)

        binding = FragmentDashboardBinding.inflate(inflater)
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

        val countrySelected = args.country.let { if (it.isEmpty()) viewModel.getLastCountry() else it }
        if (countrySelected == viewModel.getLastCountry()) {
            handleResponse(viewModel.productItems)
        } else viewModel.clearAll()

        viewModel.responseLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is DashBoardEvents.ProductsSuccess -> handleNewResponse(it.getDataOnce())
                is DashBoardEvents.ProductsPaginationSuccess -> handlePaginationResponse(it.getDataOnce())
                is DashBoardEvents.ErrorMessage -> showToastMessage(it.getDataOnce())
                else -> Unit
            }
        })

        binding.recyclerViewProducts.addOnScrollListener(
            object : PaginationScrollListener(gridLayoutManager, 50) {
                override fun onLoadMore(currentPage: Int, totalItemCount: Int) {
                    Timber.i("currentPage: $currentPage + totalItemCount: $totalItemCount")
                    viewModel.getProductsByPagination(totalItemCount)
                }
            }
        )

        binding.searchEditText.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.performFirstSearch(textView.text.toString())
                hideKeyboardFrom(requireActivity().baseContext, textView)
                true
            } else false
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun handleNewResponse(productModels: List<Product>?) {
        binding.recyclerViewProducts.smoothScrollToPosition(0)
        handleResponse(productModels)
    }

    private fun handleResponse(productModels: List<Product>?) {
        productModels?.map {
            DashBoardItem(it, R.layout.item_dashboard, BR.model)
        }?.let {
            productAdapter.clearAndUpdateData(it)
        }
    }

    private fun handlePaginationResponse(productModels: List<Product>?) {
        productModels?.map {
            DashBoardItem(it, R.layout.item_dashboard, BR.model)
        }?.let {
            productAdapter.updateData(it)
        }
    }

    override fun onClickItem(item: GenericItem<Product>) {
        (item as DashBoardItem).binding?.imageViewPicture?.let {
            val extras = FragmentNavigatorExtras(it to item.product.meliId)
            val action =
                DashBoardFragmentDirections.actionDashboardFragmentToDetailFragment(item.data)
            findNavController().navigate(action, extras)
        }
    }

    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(COUNTRY_SELECTED_KEY, viewModel.getLastCountry())
    }

}
