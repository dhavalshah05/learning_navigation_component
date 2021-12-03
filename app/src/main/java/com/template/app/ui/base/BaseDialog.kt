package com.template.app.ui.base

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.template.app.R
import com.template.app.data.local.preference.Session
import com.template.app.util.alert.AlertNotification
import com.template.app.util.keyboard.KeyboardVisibilityHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseDialog : DialogFragment() {

    @Inject
    lateinit var session: Session

    @Inject
    lateinit var keyboardVisibilityHandler: KeyboardVisibilityHandler

    @Inject
    lateinit var alertNotification: AlertNotification

    /*
    * ****************************************
    * Lifecycle Functions
    * ****************************************
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    /**
     *
     */
    fun showSuccessMessage(message: String) {
        alertNotification.showSuccessMessage(
            message = message,
            dialog = dialog
        )
    }

    /**
     *
     */
    fun showErrorMessage(message: String) {
        alertNotification.showErrorMessage(
            message = message,
            dialog = dialog
        )
    }

}