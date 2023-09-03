package com.owrori.android.ui.auth.view

import android.os.Handler
import android.os.Looper
import com.owrori.android.ui.common.presentation.view.BaseFragment
import com.owrori.android.R
import com.owrori.android.databinding.FragmentSplashBinding
import com.owrori.android.ui.auth.viewmodel.SplashViewModel
import com.owrori.android.ui.common.navigateTo
import org.koin.android.ext.android.inject

class SplashFragment :
    BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {
    override val viewModel: SplashViewModel by inject()
    override fun setBindingVariables() {}

    override fun initView() {
        navigateToLogin()
    }

    override fun initObserver() {}

    private fun navigateToLogin() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateTo(R.id.action_splashFragment_to_onboardingFragment)
        }, 1500)
    }
}