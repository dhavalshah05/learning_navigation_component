package com.template.app.ui.demoappnavigation

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.template.app.R
import timber.log.Timber

class Navigator(
    private val navController: NavController
) {

    @SuppressLint("RestrictedApi")
    fun printBackStack() {
        val iterator = navController.backStack.iterator()
        var stack = ""
        while (iterator.hasNext()) {
            val label = iterator.next().destination.label
            if (label != null) {
                stack = stack.plus("$label, ")
            }
        }
        Timber.d(stack)
    }

    fun addDestinationChangeListener(listener: NavController.OnDestinationChangedListener) {
        navController.addOnDestinationChangedListener(listener)
    }

    fun removeDestinationChangeListener(listener: NavController.OnDestinationChangedListener) {
        navController.removeOnDestinationChangedListener(listener)
    }

    fun splashToLogin() {
        val options = NavOptions.Builder()
            .setPopUpTo(R.id.fragmentSplash, true)
            .build()
        navController.navigate(R.id.navigateActionLogin, null, options)
    }

    fun loginToCreateAccount() {
        navController.navigate(R.id.navigateActionCreateAccount)
    }

    fun loginToVerifyOtp() {
        navController.navigate(R.id.navigateActionVerifyOtp)
    }

    fun createAccountToCompleteProfile() {
        navController.navigate(R.id.navigateActionCompleteProfile)
    }

    fun completeProfileToHome() {
        val options = NavOptions.Builder()
            .setPopUpTo(R.id.fragmentLogin, true)
            .build()
        navController.navigate(R.id.navigateActionHome, null, options)
    }

    fun verifyOtpToHome() {
        val options = NavOptions.Builder()
            .setPopUpTo(R.id.fragmentLogin, true)
            .build()
        navController.navigate(R.id.navigateActionHome, null, options)
    }

    fun toTabHome() {
        val options = NavOptions.Builder()
            .setPopUpTo(R.id.fragmentHome, false)
            .setLaunchSingleTop(true)
            .build()
        navController.navigate(R.id.navigateActionHome, null, options)
    }

    fun toTabOrders() {
        val options = NavOptions.Builder()
            .setPopUpTo(R.id.fragmentOrders, false)
            .setLaunchSingleTop(true)
            .build()
        navController.navigate(R.id.navigateActionOrders, null, options)
    }

    fun toTabSettings() {
        val options = NavOptions.Builder()
            .setPopUpTo(R.id.fragmentSettings, false)
            .setLaunchSingleTop(true)
            .build()
        navController.navigate(R.id.navigateActionSettings, null, options)
    }

    fun settingToSettingDetail() {
        navController.navigate(R.id.navigateActionSettingDetail)
    }

}