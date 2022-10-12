package com.template.app.ui.demoappnavigation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.template.app.R
import com.template.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.buttonCreateAccount).setOnClickListener {
            findNavController().navigate(R.id.navigateActionCreateAccount)
        }
        view.findViewById<AppCompatButton>(R.id.buttonVerifyOtp).setOnClickListener {
            findNavController().navigate(R.id.navigateActionVerifyOtp)
        }
    }
}