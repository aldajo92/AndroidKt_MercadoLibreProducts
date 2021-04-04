package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.projects.aldajo92.mercadolibreproducts.BR
import com.projects.aldajo92.mercadolibreproducts.R
import com.projects.aldajo92.mercadolibreproducts.databinding.FragmentDashboardBinding
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.GenericAdapter
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.GenericItem
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject


class DashBoardFragment : BaseFragment(), DashBoardListener<Product> {

    @Inject
    lateinit var viewModel: DashBoardViewModel

    private lateinit var binding: FragmentDashboardBinding

    private val productAdapter by lazy {
        GenericAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.response.observe(viewLifecycleOwner, {
            when (it) {
                is DashBoardEvents.ProductsSuccess -> handleResponse(it.productModels)
                is DashBoardEvents.ErrorMessage -> Timber.e(it.message)
            }
        })

        binding.recyclerViewProducts.adapter = productAdapter

        binding.recyclerViewProducts.layoutManager = GridLayoutManager(
            activity,
            calculateBestSpanCount(resources.getDimensionPixelSize(R.dimen.width_image_home))
        );

        binding.searchEditText.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.performSearch(textView.text.toString())
                true
            } else false
        }
    }

    private fun handleResponse(productModels: List<Product>) {
        val itemList = productModels.map {
            DashBoardItem(it, R.layout.item_dashboard, BR.model)
        }
        productAdapter.updateData(itemList)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onClickItem(item: GenericItem<Product>) {
        val action = DashBoardFragmentDirections.actionDashboardFragmentToDetailFragment(item.data)
        findNavController().navigate(action)
    }

    private fun calculateBestSpanCount(posterWidth: Int): Int {
        val screenWidth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.windowManager?.currentWindowMetrics?.let { windowMetrics ->
                val insets = windowMetrics
                    .windowInsets
                    .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
                windowMetrics.bounds.width() - insets.left - insets.right
            } ?: 0
        } else {
            val displayMetrics = DisplayMetrics()
            activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
        return (screenWidth / posterWidth)
    }

}
