package com.template.app.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.IdRes
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
        when (destination.id) {
            R.id.fragmentHome -> {
                bottomNavigation.visibility = View.VISIBLE
                selectBottomNavigationWithoutListener(R.id.bottomNavigationExhibitions)
            }
            R.id.fragmentOrders -> {
                bottomNavigation.visibility = View.VISIBLE
                selectBottomNavigationWithoutListener(R.id.bottomNavigationOrders)
            }
            R.id.fragmentSettings -> {
                bottomNavigation.visibility = View.VISIBLE
                selectBottomNavigationWithoutListener(R.id.bottomNavigationSettings)
            }
            else -> {
                bottomNavigation.visibility = View.GONE
            }
        }

        printBackStack()
    }

    private val bottomNavigationSelectListener = NavigationBarView.OnItemSelectedListener {
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

    private fun printBackStack() {
        val iterator = navHostFragment.findNavController().backStack.iterator()
        var stack = ""
        while (iterator.hasNext()) {
            val label = iterator.next().destination.label
            if (label != null) {
                stack = stack.plus("$label, ")
            }
        }
        Timber.d(stack)
    }

    private fun selectBottomNavigationWithoutListener(@IdRes id: Int) {
        bottomNavigation.setOnItemSelectedListener(null)
        bottomNavigation.selectedItemId = id
        bottomNavigation.setOnItemSelectedListener(bottomNavigationSelectListener)
    }

}