package com.template.app.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.template.app.R
import com.template.app.databinding.ActivityMainBinding
import com.template.app.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var navigator: Navigator

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        navigator.init(R.id.navHostFragment, savedInstanceState, bottomNavigationController)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        navigator.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (!navigator.goBack()) {
            super.onBackPressed()
        }
    }

}