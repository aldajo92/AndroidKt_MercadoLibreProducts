package com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.projects.aldajo92.mercadolibreproducts.BR
import com.projects.aldajo92.mercadolibreproducts.R
import com.projects.aldajo92.mercadolibreproducts.databinding.FragmentDashboardBinding
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.BaseFragment
import com.projects.aldajo92.mercadolibreproducts.presentation.recycleradapter.RecyclerItem
import com.projects.aldajo92.mercadolibreproducts.presentation.recycleradapter.RecyclerViewAdapter
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject


class DashBoardFragment : BaseFragment() {

    @Inject
    lateinit var viewModel : DashBoardViewModel

    private lateinit var binding: FragmentDashboardBinding

    private val productAdapter by lazy {
        RecyclerViewAdapter()
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
                is DashBoardEvent.ProductsSuccess -> handleResponse(it.productModels)
                is DashBoardEvent.ErrorMessage -> Timber.e(it.message)
            }
        })

        binding.recyclerViewProducts.adapter = productAdapter
        binding.recyclerViewProducts.layoutManager =
            LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )

        binding.searchEditText.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.performSearch(textView.text.toString())
                true
            } else false
        }
    }

    private fun handleResponse(productModels: List<Product>) {
        val itemList = productModels.map {
            RecyclerItem(it, R.layout.item_product, BR.model)
        }
        productAdapter.updateData(itemList)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}