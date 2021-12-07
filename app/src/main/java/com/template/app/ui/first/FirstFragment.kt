package com.template.app.ui.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.app.databinding.FragmentFirstBinding
import com.template.app.ui.Navigator
import com.template.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("lifecycle_ob").d("onCreate - First")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag("lifecycle_ob").d("onDestroy - First")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        Timber.tag("lifecycle_ob").d("onCreateView - First")
        setListeners()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.tag("lifecycle_ob").d("onDestroyView - First")
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
    }

    private fun navigateToGlobalScreen() {
        navigator.navigateToGlobalScreen()
    }

    private fun navigateToSelectAddressScreen() {
        navigator.navigateToSelectAddressScreen()
    }

    private fun navigateToSecondScreen() {
        navigator.navigateToSecondScreen()
    }

    private fun navigateToGlobalScreenAndClearStack() {
        navigator.navigateToGlobalScreenAndClearStack()
    }
}