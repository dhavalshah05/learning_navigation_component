package com.template.app.ui

import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.template.app.ui.address.edit.EditAddressFragment
import com.template.app.ui.address.select.SelectAddressFragment
import com.template.app.ui.detail.DetailFragment
import com.template.app.ui.global.GlobalFragment
import com.template.app.ui.second.SecondFragment
import com.template.app.ui.third.ThirdFragment

class AppNavigator(
    fragmentManager: FragmentManager
) : Navigator(fragmentManager) {

    fun navigateToSecondScreen() {
        fragNavController.pushFragment(SecondFragment())
    }

    fun navigateToGlobalScreen() {
        fragNavController.pushFragment(GlobalFragment())
    }

    fun navigateToSelectAddressScreen() {
        fragNavController.pushFragment(SelectAddressFragment())
    }

    fun navigateToGlobalScreenAndClearStack() {
        fragNavController.clearStack()
        fragNavController.replaceFragment(GlobalFragment())
    }

    fun navigateToThirdScreen() {
        fragNavController.pushFragment(ThirdFragment())
    }

    fun navigateToEditAddressScreen() {
        fragNavController.pushFragment(EditAddressFragment())
    }

    fun switchToHomeTab() {
        fragNavController.switchTab(FragNavController.TAB1)
    }

    fun switchToSettingsTab() {
        fragNavController.switchTab(FragNavController.TAB2)
    }

    fun openDetailScreen() {
        fragNavController.pushFragment(DetailFragment())
    }

}