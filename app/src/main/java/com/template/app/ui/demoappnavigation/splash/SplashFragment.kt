package com.template.app.ui.demoappnavigation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.app.R
import com.template.app.ui.MainActivity
import com.template.app.ui.base.BaseFragment
import com.template.app.ui.demoappnavigation.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private val navigator: Navigator by lazy {
        (requireActivity() as MainActivity).getNavigator()
    }

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable {
        navigator.splashToLogin()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
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