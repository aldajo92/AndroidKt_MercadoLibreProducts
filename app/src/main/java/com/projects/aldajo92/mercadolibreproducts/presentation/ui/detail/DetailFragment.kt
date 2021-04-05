package com.projects.aldajo92.mercadolibreproducts.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.projects.aldajo92.mercadolibreproducts.databinding.FragmentDetailBinding
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.BaseFragment
import javax.inject.Inject

class DetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: DetailViewModel

    private lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigation(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = args.product
        binding.model = product
        binding.viewModel = viewModel
        viewModel.getProductInformation(product)

        Glide.with(this)
            .load("https://http2.mlstatic.com/D_${product.imgId}-O.jpg")
            .into(binding.imageViewPicture)

        binding.executePendingBindings()
    }


}
