package com.template.app.ui.base

import androidx.fragment.app.Fragment
import com.template.app.data.local.preference.Session
import com.template.app.util.alert.AlertNotification
import com.template.app.util.keyboard.KeyboardVisibilityHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var session: Session

    @Inject
    lateinit var keyboardVisibilityHandler: KeyboardVisibilityHandler

    @Inject
    lateinit var alertNotification: AlertNotification

    /**
     *
     */
    fun showSuccessMessage(message: String) {
        alertNotification.showSuccessMessage(
            message = message,
            activity = requireActivity()
        )
    }

    /**
     *
     */
    fun showErrorMessage(message: String) {
        alertNotification.showErrorMessage(
            message = message,
            activity = requireActivity()
        )
    }

}