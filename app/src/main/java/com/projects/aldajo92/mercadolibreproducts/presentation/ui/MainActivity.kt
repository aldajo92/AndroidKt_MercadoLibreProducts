package com.projects.aldajo92.mercadolibreproducts.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.projects.aldajo92.mercadolibreproducts.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var bottomNavView: BottomNavigationView

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        bottomNavView = binding.bottomNavView
        navHostFragment =
            (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment)

        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
    }

    fun showBottomNavigation() {
        bottomNavView.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        bottomNavView.visibility = View.GONE
    }
}
