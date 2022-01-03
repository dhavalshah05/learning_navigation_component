package com.template.app.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.app.databinding.FragmentSecondBinding
import com.template.app.ui.AppNavigator
import com.template.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondFragment : BaseFragment() {

    @Inject
    lateinit var navigator: AppNavigator

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        setListeners()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        binding.buttonNavigateToThirdScreen.setOnClickListener {
            navigateToThirdScreen()
        }
    }

    private fun navigateToThirdScreen() {
        navigator.navigateToThirdScreen()
    }

}