package com.projects.aldajo92.mercadolibreproducts.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.projects.aldajo92.mercadolibreproducts.databinding.FragmentDetailBinding
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.BaseFragment
import javax.inject.Inject

class DetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: DetailViewModel

    private lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.model = args.product
        viewModel.product
    }

}
