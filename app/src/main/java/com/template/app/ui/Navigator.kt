package com.template.app.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.template.app.ui.address.edit.EditAddressFragment
import com.template.app.ui.address.select.SelectAddressFragment
import com.template.app.ui.first.FirstFragment
import com.template.app.ui.global.GlobalFragment
import com.template.app.ui.second.SecondFragment
import com.template.app.ui.third.ThirdFragment

class Navigator(
    fragmentManager: FragmentManager,
    @IdRes placeHolder: Int
) {

    private val fragNavController: FragNavController = FragNavController(fragmentManager, placeHolder)

    fun init(rootFragment: Fragment, savedInstanceState: Bundle?) {
        fragNavController.rootFragmentListener = object : FragNavController.RootFragmentListener {
            override val numberOfRootFragments: Int
                get() = 1

            override fun getRootFragment(index: Int): Fragment {
                return rootFragment
            }
        }
        fragNavController.initialize(FragNavController.TAB1, savedInstanceState)
    }

    fun onSaveInstanceState(outState: Bundle) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun navigateToSecondScreen() {
        fragNavController.pushFragment(SecondFragment())
    }

    fun goBack(): Boolean {
        return if (fragNavController.isRootFragment) {
            false
        } else {
            fragNavController.popFragment()
        }
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

    fun goToRoot() {
        fragNavController.clearStack()
    }

    fun navigateToEditAddressScreen() {
        fragNavController.pushFragment(EditAddressFragment())
    }

}