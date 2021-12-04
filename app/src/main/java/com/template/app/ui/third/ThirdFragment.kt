package com.template.app.ui.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.template.app.R
import com.template.app.databinding.FragmentThirdBinding
import com.template.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : BaseFragment() {

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
        findNavController().popBackStack()
    }

    private fun goBackToFirstScreen() {
        findNavController().popBackStack(R.id.fragmentFirst, false)
    }
}