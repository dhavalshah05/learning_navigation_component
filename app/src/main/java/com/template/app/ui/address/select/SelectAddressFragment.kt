package com.template.app.ui.address.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.app.databinding.FragmentSelectAddressBinding
import com.template.app.ui.MainNavigator
import com.template.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SelectAddressFragment : BaseFragment() {

    @Inject
    lateinit var navigator: MainNavigator

    private var _binding: FragmentSelectAddressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectAddressBinding.inflate(inflater, container, false)
        initListeners()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners() {
        binding.buttonEditAddress.setOnClickListener {
            navigateToEditAddressScreen()
        }
        binding.buttonNavigateToGlobalScreen.setOnClickListener {
            navigateToGlobalScreen()
        }
    }

    private fun navigateToGlobalScreen() {
        navigator.navigateToGlobalScreen()
    }

    private fun navigateToEditAddressScreen() {
        navigator.navigateToEditAddressScreen()
    }
}