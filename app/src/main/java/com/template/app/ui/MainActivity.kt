package com.template.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import com.template.app.R
import com.template.app.ui.base.BaseActivity
import com.template.app.ui.first.FirstFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val navigator = Navigator(supportFragmentManager, R.id.navHostFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.init(FirstFragment(), savedInstanceState)
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