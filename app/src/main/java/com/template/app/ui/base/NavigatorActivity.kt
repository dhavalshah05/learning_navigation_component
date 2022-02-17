package com.template.app.ui.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.template.app.ui.AppNavigator
import com.template.app.ui.BottomNavigationController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class NavigatorActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.init(getNavigationHostFragmentId(), savedInstanceState, getRootFragments(), provideBottomNavigationController())
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

    open fun provideBottomNavigationController(): BottomNavigationController? {
        return null
    }

    @IdRes
    abstract fun getNavigationHostFragmentId(): Int

    abstract fun getRootFragments(): List<Fragment>
}