package com.template.app.ui

import androidx.fragment.app.FragmentManager
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import kotlin.reflect.KClass

@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class Navigator(
    private val fragmentManager: FragmentManager
) {

    protected lateinit var fragNavController: FragNavController
    private var bottomNavigationController: BottomNavigationController? = null

    /**
     *
     */
    fun init(
        @IdRes placeHolder: Int,
        savedInstanceState: Bundle?,
        bottomNavigationController: BottomNavigationController? = null
    ) {
        fragNavController = FragNavController(fragmentManager, placeHolder)
        this.bottomNavigationController = bottomNavigationController

        initTabsAndRootFragments()
        initTransactionListener()

        fragNavController.initialize(FragNavController.TAB1, savedInstanceState)
    }

    /**
     *
     */
    fun onSaveInstanceState(outState: Bundle) {
        fragNavController.onSaveInstanceState(outState)
    }

    /**
     *
     */
    fun goBack(): Boolean {
        return if (fragNavController.isRootFragment) {
            false
        } else {
            fragNavController.popFragment()
        }
    }

    /**
     *
     */
    fun goToRoot() {
        fragNavController.clearStack()
    }

    /**
     *
     */
    fun popFragmentTo(fragmentClass: KClass<*>) {
        fragNavController.currentStack?.let { stack ->
            val size = stack.size
            var indexOfFragment = -1

            for (i in stack.indices) {
                val fragment = stack[i]
                if (fragmentClass.isInstance(fragment)) {
                    indexOfFragment = i + 1
                }
            }

            if (indexOfFragment != -1) {
                val popTo = size - indexOfFragment
                fragNavController.popFragments(popTo)
            }
        }
    }

    /**
     *
     */
    private fun initTabsAndRootFragments() {
        fragNavController.rootFragmentListener = object : FragNavController.RootFragmentListener {
            override val numberOfRootFragments: Int
                get() = getRootFragments().size

            override fun getRootFragment(index: Int): Fragment {
                return getRootFragments()[index]
            }
        }
    }

    /**
     *
     */
    private fun initTransactionListener() {
        fragNavController.transactionListener = object : FragNavController.TransactionListener {
            override fun onFragmentTransaction(
                fragment: Fragment?,
                transactionType: FragNavController.TransactionType
            ) {
                if (fragment == null) {
                    return
                }

                if (isFragmentPartOfBottomNavigation(fragment)) {
                    bottomNavigationController?.showBottomNavigation()
                } else {
                    bottomNavigationController?.hideBottomNavigation()
                }
            }

            override fun onTabTransaction(fragment: Fragment?, index: Int) {
            }

        }
    }

    /**
     *
     */
    open fun isFragmentPartOfBottomNavigation(fragment: Fragment): Boolean {
        return false
    }

    abstract fun getRootFragments(): List<Fragment>

}