package com.template.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.template.app.R
import com.template.app.databinding.ActivityMainBinding
import com.template.app.ui.base.BaseActivity
import com.template.app.ui.first.FirstFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val navigator = Navigator(supportFragmentManager, R.id.navHostFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigator.init(savedInstanceState)

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

    fun getNavigator() = navigator
}