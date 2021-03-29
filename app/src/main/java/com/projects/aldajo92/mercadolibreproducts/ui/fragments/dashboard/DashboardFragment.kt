package com.projects.aldajo92.mercadolibreproducts.ui.fragments.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.projects.aldajo92.mercadolibreproducts.BR
import com.projects.aldajo92.mercadolibreproducts.R
import com.projects.aldajo92.mercadolibreproducts.databinding.FragmentDashboardBinding
import com.projects.aldajo92.mercadolibreproducts.models.MeliProduct
import com.projects.aldajo92.mercadolibreproducts.ui.BaseFragment
import com.projects.aldajo92.mercadolibreproducts.ui.recycleradapter.RecyclerItem
import com.projects.aldajo92.mercadolibreproducts.ui.recycleradapter.RecyclerViewAdapter
import com.projects.aldajo92.mercadolibreproducts.viewModels.DashBoardViewModel
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

class DashboardFragment : BaseFragment() {

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

        viewModel.getMeliResponse()

        viewModel.response.observe(viewLifecycleOwner, {
            when(it){
                is DashBoardEvent.ProductsSuccess -> handleResponse(it.products)
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
    }

    private fun handleResponse(products: List<MeliProduct>) {
        val itemList = products.map {
            RecyclerItem(it, R.layout.item_product, BR.model)
        }
        productAdapter.updateData(itemList)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}