package com.owori.android.presenter.splash

import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.owori.android.core.BaseFragment
import com.owori.android.R
import com.owori.android.databinding.FragmentSplashBinding
import com.owori.android.core.navigateTo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {
    override val viewModel: SplashViewModel by viewModels()
    override fun setBindingVariables() {}

    override fun initView() {
        navigateToLogin()
    }

    override fun initObserver() {}

    override fun onResume() {
        super.onResume()
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.owori_red)
    }

    override fun onStop() {
        super.onStop()
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
    }

    private fun navigateToLogin() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateTo(R.id.action_splashFragment_to_onboardingFragment)
        }, 1500)
    }
}