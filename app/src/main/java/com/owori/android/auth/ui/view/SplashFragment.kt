package com.owori.android.auth.ui.view

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import com.owori.android.common.ui.view.BaseFragment
import com.owori.android.R
import com.owori.android.databinding.FragmentSplashBinding
import com.owori.android.auth.ui.viewmodel.SplashViewModel
import com.owori.android.common.navigateTo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {
    override val viewModel: SplashViewModel by viewModels()
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