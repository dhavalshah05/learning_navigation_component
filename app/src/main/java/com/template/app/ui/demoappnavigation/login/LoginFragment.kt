package com.template.app.ui.demoappnavigation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.template.app.R
import com.template.app.ui.MainActivity
import com.template.app.ui.base.BaseFragment
import com.template.app.ui.demoappnavigation.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private val navigator: Navigator by lazy { (requireActivity() as MainActivity).getNavigator() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.buttonCreateAccount).setOnClickListener {
            navigator.loginToCreateAccount()
        }
        view.findViewById<AppCompatButton>(R.id.buttonVerifyOtp).setOnClickListener {
            navigator.loginToVerifyOtp()
        }
    }
}