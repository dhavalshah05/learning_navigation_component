package com.template.app.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.template.app.ui.address.edit.EditAddressFragment
import com.template.app.ui.address.select.SelectAddressFragment
import com.template.app.ui.detail.DetailFragment
import com.template.app.ui.first.FirstFragment
import com.template.app.ui.global.GlobalFragment
import com.template.app.ui.home.HomeFragment
import com.template.app.ui.second.SecondFragment
import com.template.app.ui.settings.SettingsFragment
import com.template.app.ui.third.ThirdFragment
import timber.log.Timber

class Navigator(
    fragmentManager: FragmentManager,
    @IdRes placeHolder: Int,
    private val bottomNavigationController: BottomNavigationController
) {

    private val fragNavController: FragNavController = FragNavController(fragmentManager, placeHolder)

    fun init(savedInstanceState: Bundle?) {
        fragNavController.rootFragmentListener = object : FragNavController.RootFragmentListener {
            override val numberOfRootFragments: Int
                get() = 2

            override fun getRootFragment(index: Int): Fragment {
                return when (index) {
                    FragNavController.TAB1 -> HomeFragment()
                    FragNavController.TAB2 -> SettingsFragment()
                    else -> throw RuntimeException("Index error")
                }
            }
        }

        fragNavController.transactionListener = object : FragNavController.TransactionListener {
            override fun onFragmentTransaction(
                fragment: Fragment?,
                transactionType: FragNavController.TransactionType
            ) {
                if (fragment == null) {
                    return
                }

                when (fragment) {
                    is HomeFragment -> {
                        bottomNavigationController.showBottomNavigation()
                    }
                    is SettingsFragment -> {
                        bottomNavigationController.showBottomNavigation()
                    }
                    else -> {
                        bottomNavigationController.hideBottomNavigation()
                    }
                }
            }

            override fun onTabTransaction(fragment: Fragment?, index: Int) {
            }

        }

        fragNavController.initialize(FragNavController.TAB1, savedInstanceState)
    }

    fun onSaveInstanceState(outState: Bundle) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun goBack(): Boolean {
        return if (fragNavController.isRootFragment) {
            false
        } else {
            fragNavController.popFragment()
        }
    }

    fun goToRoot() {
        fragNavController.clearStack()
    }

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