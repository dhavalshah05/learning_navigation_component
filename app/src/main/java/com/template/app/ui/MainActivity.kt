package com.template.app.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.template.app.R
import com.template.app.ui.base.BaseActivity
import com.template.app.ui.demoappnavigation.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNavigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navigator = Navigator(navController = navHostFragment.navController)

        navigator.addDestinationChangeListener(destinationChangeListener)
        bottomNavigation.setOnItemSelectedListener(bottomNavigationSelectListener)
        bottomNavigation.setOnItemReselectedListener {  }
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.removeDestinationChangeListener(destinationChangeListener)
        bottomNavigation.setOnItemSelectedListener(null)
    }

    fun getNavigator(): Navigator {
        return navigator
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

        navigator.printBackStack()
    }

    private val bottomNavigationSelectListener = NavigationBarView.OnItemSelectedListener {
        if (it.itemId == R.id.bottomNavigationExhibitions) {
            navigator.toTabHome()
            return@OnItemSelectedListener true
        }
        if (it.itemId == R.id.bottomNavigationOrders) {
            navigator.toTabOrders()
            return@OnItemSelectedListener true
        }
        if (it.itemId == R.id.bottomNavigationSettings) {
            navigator.toTabSettings()
            return@OnItemSelectedListener true
        }
        false
    }

    private fun selectBottomNavigationWithoutListener(@IdRes id: Int) {
        bottomNavigation.setOnItemSelectedListener(null)
        bottomNavigation.selectedItemId = id
        bottomNavigation.setOnItemSelectedListener(bottomNavigationSelectListener)
    }

}