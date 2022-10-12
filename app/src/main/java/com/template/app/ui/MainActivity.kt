package com.template.app.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
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
        bottomNavigation.setOnItemReselectedListener {  }
    }

    override fun onDestroy() {
        super.onDestroy()
        navHostFragment.findNavController().removeOnDestinationChangedListener(destinationChangeListener)
        bottomNavigation.setOnItemSelectedListener(null)
    }

    private val destinationChangeListener = NavController.OnDestinationChangedListener { _, destination, _ ->
        if (destination.id == R.id.fragmentHome || destination.id == R.id.fragmentOrders || destination.id == R.id.fragmentSettings) {
            bottomNavigation.visibility = View.VISIBLE
        } else {
            bottomNavigation.visibility = View.GONE
        }

        if (destination.id == R.id.fragmentHome) {
            bottomNavigation.setOnItemSelectedListener(null)
            bottomNavigation.selectedItemId = R.id.bottomNavigationExhibitions
            bottomNavigation.setOnItemSelectedListener(bottomNavigationSelectListener)
        }
        if (destination.id == R.id.fragmentOrders) {
            bottomNavigation.setOnItemSelectedListener(null)
            bottomNavigation.selectedItemId = R.id.bottomNavigationOrders
            bottomNavigation.setOnItemSelectedListener(bottomNavigationSelectListener)
        }
        if (destination.id == R.id.fragmentSettings) {
            bottomNavigation.setOnItemSelectedListener(null)
            bottomNavigation.selectedItemId = R.id.bottomNavigationSettings
            bottomNavigation.setOnItemSelectedListener(bottomNavigationSelectListener)
        }

        val iterator = navHostFragment.findNavController().backStack.iterator()
        var stack = ""
        while (iterator.hasNext()) {
            stack = "$stack, ${iterator.next().destination.label}"
        }
        Timber.d(stack)
    }

    private val bottomNavigationSelectListener = NavigationBarView.OnItemSelectedListener {
        Timber.d("onBottomNav clicked: ${it.itemId}")

        if (it.itemId == R.id.bottomNavigationExhibitions) {
            val options = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentHome, false)
                .setLaunchSingleTop(true)
                .build()
            navHostFragment.findNavController().navigate(R.id.navigateActionHome, null, options)
            return@OnItemSelectedListener true
        }
        if (it.itemId == R.id.bottomNavigationOrders) {
            val options = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentOrders, false)
                .setLaunchSingleTop(true)
                .build()
            navHostFragment.findNavController().navigate(R.id.navigateActionOrders, null, options)
            return@OnItemSelectedListener true
        }
        if (it.itemId == R.id.bottomNavigationSettings) {
            val options = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentSettings, false)
                .setLaunchSingleTop(true)
                .build()
            navHostFragment.findNavController().navigate(R.id.navigateActionSettings, null, options)
            return@OnItemSelectedListener true
        }
        false
    }

}