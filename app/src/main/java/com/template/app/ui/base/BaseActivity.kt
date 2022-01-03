package com.template.app.ui.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.template.app.data.local.preference.Session
import com.template.app.exception.defaultexceptionhandler.ExceptionHandler
import com.template.app.ui.BottomNavigationController
import com.template.app.ui.AppNavigator
import com.template.app.util.alert.AlertNotification
import com.template.app.util.keyboard.KeyboardVisibilityHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var session: Session

    @Inject
    lateinit var keyboardVisibilityHandler: KeyboardVisibilityHandler

    @Inject
    lateinit var alertNotification: AlertNotification

    @Inject
    lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        ExceptionHandler.register(this)
        super.onCreate(savedInstanceState)
        // Init navigator
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

    /**
     *
     */
    fun showSuccessMessage(message: String) {
        alertNotification.showSuccessMessage(
            message = message,
            activity = this
        )
    }

    /**
     *
     */
    fun showErrorMessage(message: String) {
        alertNotification.showErrorMessage(
            message = message,
            activity = this
        )
    }

    /**
     *
     */
    open fun provideBottomNavigationController(): BottomNavigationController? {
        return null
    }

    @IdRes
    abstract fun getNavigationHostFragmentId(): Int

    abstract fun getRootFragments(): List<Fragment>

}