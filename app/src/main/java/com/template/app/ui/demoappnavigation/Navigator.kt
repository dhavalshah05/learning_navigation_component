package com.template.app.ui.demoappnavigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.template.app.R

class Navigator(
    private val navController: NavController
) {

    private val startDestination
        get() = navController.graph.startDestination

    fun navigateToLoginScreen() {
        val options = NavOptions.Builder()
            .setPopUpTo(startDestination, true)
            .build()
        navController.navigate(R.id.navigateActionLogin, null, options)
    }

}