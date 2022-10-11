package com.template.app.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.template.app.R
import com.template.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.buttonFutureOrders).setOnClickListener {
            val navHostFragment =
                childFragmentManager.findFragmentById(R.id.navHostFragmentOrders) as NavHostFragment

            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentPastOrders, true)
                .build()
            navHostFragment.navController.navigate(R.id.navigateActionFutureOrders, null, navOptions)
        }
        view.findViewById<AppCompatButton>(R.id.buttonPastOrders).setOnClickListener {
            val navHostFragment =
                childFragmentManager.findFragmentById(R.id.navHostFragmentOrders) as NavHostFragment
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentFutureOrders, true)
                .build()
            navHostFragment.navController.navigate(R.id.navigateActionPastOrders, null, navOptions)
        }
    }

}