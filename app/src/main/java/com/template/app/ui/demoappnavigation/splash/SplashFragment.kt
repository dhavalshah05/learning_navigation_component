package com.template.app.ui.demoappnavigation.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.template.app.R
import com.template.app.ui.MainActivity
import com.template.app.ui.base.BaseFragment
import com.template.app.ui.demoappnavigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private lateinit var navigator: Navigator

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable {
        navigator.navigateToLoginScreen()
    }

    private val activityLifecycleObserver = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            navigator = (requireActivity() as MainActivity).getNavigator()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.lifecycle?.addObserver(activityLifecycleObserver)
    }

    override fun onDetach() {
        super.onDetach()
        activity?.lifecycle?.removeObserver(activityLifecycleObserver)
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