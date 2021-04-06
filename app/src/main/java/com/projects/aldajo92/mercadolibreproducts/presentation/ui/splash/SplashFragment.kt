package com.projects.aldajo92.mercadolibreproducts.presentation.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.projects.aldajo92.mercadolibreproducts.BR
import com.projects.aldajo92.mercadolibreproducts.R
import com.projects.aldajo92.mercadolibreproducts.databinding.FragmentSplashBinding
import com.projects.aldajo92.mercadolibreproducts.domain.Country
import com.projects.aldajo92.mercadolibreproducts.domain.Product
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.GenericAdapter
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.GenericItem
import com.projects.aldajo92.mercadolibreproducts.presentation.generic_adapter.ItemListener
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.BaseFragment
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.dashboard.adapter.DashBoardItem
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.favorites.FavoritesFragmentDirections
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.splash.adapter.CountryItem
import com.projects.aldajo92.mercadolibreproducts.presentation.utils.calculateBestSpanCount
import timber.log.Timber
import javax.inject.Inject

class SplashFragment : BaseFragment(), ItemListener<Country> {

    @Inject
    lateinit var viewModel: SplashViewModel

    private lateinit var binding: FragmentSplashBinding

    private lateinit var countryAdapter: GenericAdapter<Country>

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showBottomNavigation(false)

        countryAdapter = GenericAdapter(this)

        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        binding = FragmentSplashBinding.inflate(inflater)

        binding.recyclerviewCountries.apply {
            layoutManager = linearLayoutManager
            adapter = countryAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCountries()

        viewModel.countriesLiveData.observe(viewLifecycleOwner, { countryEvent ->
            when (countryEvent) {
                is CountryEvent.CountriesSuccess -> handleResponse(countryEvent.getDataOnce())
                else -> Unit
            }
        })
    }

    private fun handleResponse(countryList: List<Country>?) {
        countryList?.sortedBy { it.name }?.map {
            CountryItem(it, R.layout.item_country, BR.model)
        }?.let {
            countryAdapter.clearAndUpdateData(it)
        }
    }

    override fun onClickItem(item: GenericItem<Country>) {
        viewModel.setCountry(item.data)
        val action =
            SplashFragmentDirections.actionSplashFragmentToDashboardFragment(item.data.countryId)
        findNavController().navigate(action)
    }

}
