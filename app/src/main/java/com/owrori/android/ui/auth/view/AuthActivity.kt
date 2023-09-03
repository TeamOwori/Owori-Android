package com.owrori.android.ui.auth.view

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.owrori.android.ui.common.presentation.view.BaseActivity
import com.owrori.android.R
import com.owrori.android.databinding.ActivityAuthBinding
import com.owrori.android.ui.auth.viewmodel.AuthViewModel
import org.koin.android.ext.android.inject

class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel>(R.layout.activity_auth) {
    override val viewModel: AuthViewModel by inject()

    override fun initView() {
        initNavGraph()
    }

    override fun initObserver() {}

    override fun setBindingVariables(binding: ActivityAuthBinding) {}

    private fun initNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.findNavController()
        navController.navInflater.inflate(R.navigation.auth_navigation).apply {
            setStartDestination(R.id.SplashFragment)
        }.run { navController.setGraph(this, null) }
    }
}