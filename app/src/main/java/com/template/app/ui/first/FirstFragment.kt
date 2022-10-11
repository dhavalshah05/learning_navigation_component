package com.template.app.ui.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.template.app.R
import com.template.app.databinding.FragmentFirstBinding
import com.template.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : BaseFragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        setListeners()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        binding.buttonNavigateToSecondScreen.setOnClickListener {
            navigateToSecondScreen()
        }
        binding.buttonNavigateToSelectAddressScreen.setOnClickListener {
            navigateToSelectAddressScreen()
        }
        binding.buttonNavigateToGlobalScreen.setOnClickListener {
            navigateToGlobalScreen()
        }
        binding.buttonNavigateToGlobalScreenAndClearStack.setOnClickListener {
            navigateToGlobalScreenAndClearStack()
        }
        binding.buttonNavigateToOrders.setOnClickListener {
            findNavController().navigate(R.id.navigateActionOrders)
        }
    }

    private fun navigateToGlobalScreen() {
        findNavController().navigate(R.id.navigateActionGlobal)
    }

    private fun navigateToSelectAddressScreen() {
        findNavController().navigate(R.id.navigateActionSelectAddress)
    }

    private fun navigateToSecondScreen() {
        findNavController().navigate(R.id.navigateActionFirstScreenToSecondScreen)
    }

    private fun navigateToGlobalScreenAndClearStack() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.fragmentFirst, true)
            .build()
        findNavController().navigate(R.id.navigateActionGlobal, null, navOptions)
    }
}