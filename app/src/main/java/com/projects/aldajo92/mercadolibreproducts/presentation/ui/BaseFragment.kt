package com.projects.aldajo92.mercadolibreproducts.presentation.ui

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    fun showToastMessage(message: String?) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    fun showBottomNavigation(showBottomNavigation: Boolean) {
        val activity = (requireActivity() as MainActivity)
        if (showBottomNavigation) activity.showBottomNavigation()
        else activity.hideBottomNavigation()
    }
}
