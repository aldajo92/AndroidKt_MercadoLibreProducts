package com.projects.aldajo92.mercadolibreproducts.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.projects.aldajo92.mercadolibreproducts.R
import com.projects.aldajo92.mercadolibreproducts.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber

class MainActivity : DaggerAppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.let {
            setupViews(it)
        }
    }

    private fun setupViews(binding: ActivityMainBinding) {
        navHostFragment =
            (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment)

        NavigationUI.setupWithNavController(binding.bottomNavView, navHostFragment.navController)
    }

    fun showBottomNavigation() {
        binding?.bottomNavView?.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        binding?.bottomNavView?.visibility = View.GONE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("save state please!")
    }
}
