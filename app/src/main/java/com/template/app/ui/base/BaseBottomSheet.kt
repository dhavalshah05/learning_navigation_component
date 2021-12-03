package com.template.app.ui.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.template.app.data.local.preference.Session
import com.template.app.util.alert.AlertNotification
import com.template.app.util.keyboard.KeyboardVisibilityHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseBottomSheet : BottomSheetDialogFragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val parent = view.parent as? View
        parent?.setBackgroundColor(Color.TRANSPARENT)
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