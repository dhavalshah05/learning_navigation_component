package com.template.app.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.template.app.R
import com.template.app.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNavigation)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        navHostFragment.findNavController().addOnDestinationChangedListener(destinationChangeListener)
        bottomNavigation.setOnItemSelectedListener(bottomNavigationSelectListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        navHostFragment.findNavController().removeOnDestinationChangedListener(destinationChangeListener)
        bottomNavigation.setOnItemSelectedListener(null)
    }

    private val destinationChangeListener = NavController.OnDestinationChangedListener { _, destination, _ ->
        if (destination.id == R.id.fragmentHome) {
            bottomNavigation.visibility = View.VISIBLE
        } else {
            bottomNavigation.visibility = View.GONE
        }
    }

    private val bottomNavigationSelectListener = NavigationBarView.OnItemSelectedListener {
        Timber.d("onItem Selected: ${it.itemId}")
        true
    }

}