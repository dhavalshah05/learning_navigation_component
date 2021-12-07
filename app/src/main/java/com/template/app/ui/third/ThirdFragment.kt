package com.template.app.ui.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.app.databinding.FragmentThirdBinding
import com.template.app.ui.Navigator
import com.template.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ThirdFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        initListeners()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners() {
        binding.buttonGoBack.setOnClickListener {
            goBack()
        }
        binding.buttonGoBackToFirstScreen.setOnClickListener {
            goBackToFirstScreen()
        }
    }

    private fun goBack() {
        navigator.goBack()
    }

    private fun goBackToFirstScreen() {
        navigator.goToRoot()
    }
}