package com.projects.aldajo92.mercadolibreproducts.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.projects.aldajo92.mercadolibreproducts.R
import com.projects.aldajo92.mercadolibreproducts.databinding.FragmentDetailBinding
import com.projects.aldajo92.mercadolibreproducts.presentation.ui.BaseFragment
import com.projects.aldajo92.mercadolibreproducts.presentation.utils.formatMeliImgUrl
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
        val isFavorite = args.isFavorite

        binding.viewModel = viewModel

        viewModel.setupProductInformation(product, isFavorite)

        Glide.with(this)
            .load(product.formatMeliImgUrl())
            .into(binding.imageViewPicture)

        binding.executePendingBindings()
    }

}
