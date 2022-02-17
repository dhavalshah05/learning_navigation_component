package com.template.app.ui.base

import android.os.Bundle
import com.template.app.data.local.preference.Session
import com.template.app.exception.defaultexceptionhandler.ExceptionHandler
import com.template.app.util.alert.AlertNotification
import com.template.app.util.keyboard.KeyboardVisibilityHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : NavigatorActivity() {

    @Inject
    lateinit var session: Session

    @Inject
    lateinit var keyboardVisibilityHandler: KeyboardVisibilityHandler

    @Inject
    lateinit var alertNotification: AlertNotification

    override fun onCreate(savedInstanceState: Bundle?) {
        ExceptionHandler.register(this)
        super.onCreate(savedInstanceState)
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

}