package com.template.app.ui.demoappnavigation.otp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.template.app.R
import com.template.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyOtpFragment : BaseFragment() {

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable {
        handleNavigation()
    }

    private fun handleNavigation() {
        val options = NavOptions.Builder()
            .setPopUpTo(findNavController().graph.first().id, true)
            .build()
        findNavController().navigate(R.id.navigateActionHome, null, options)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.verify_otp_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed(runnable, 3000)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }
}