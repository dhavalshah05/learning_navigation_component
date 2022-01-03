package com.template.app.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.template.app.R
import com.template.app.databinding.ActivityMainBinding
import com.template.app.ui.base.BaseActivity
import com.template.app.ui.home.HomeFragment
import com.template.app.ui.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private val bottomNavigationController = object : BottomNavigationController {
        override fun hideBottomNavigation() {
            binding.bottomNavigation.visibility = View.GONE
        }

        override fun showBottomNavigation() {
            binding.bottomNavigation.visibility = View.VISIBLE
        }
    }

    override fun getNavigationHostFragmentId(): Int {
        return R.id.navHostFragment
    }

    override fun getRootFragments(): List<Fragment> {
        return listOf(
            HomeFragment(),
            SettingsFragment()
        )
    }

    override fun provideBottomNavigationController(): BottomNavigationController {
        return bottomNavigationController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            if (it.itemId == R.id.fragmentHome) {
                navigator.switchToHomeTab()
            } else if (it.itemId == R.id.fragmentSettings) {
                navigator.switchToSettingsTab()
            }
            true
        }

    }

}