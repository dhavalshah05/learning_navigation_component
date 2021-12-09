package com.template.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.app.databinding.FragmentHomeBinding
import com.template.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("lifecycle_log").d("onCreate - Home")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag("lifecycle_log").d("onDestroy - Home")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        Timber.tag("lifecycle_log").d("onCreateView - Home")
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Timber.tag("lifecycle_log").d("onDestroyView - Home")
    }

}