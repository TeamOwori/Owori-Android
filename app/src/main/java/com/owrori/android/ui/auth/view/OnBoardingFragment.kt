package com.owrori.android.ui.auth.view

import androidx.activity.addCallback
import com.owrori.android.ui.auth.viewmodel.LoginViewModel
import com.owrori.android.ui.common.navigateTo
import com.owrori.android.ui.common.presentation.view.BaseFragment
import com.owrori.android.R
import com.owrori.android.databinding.FragmentLoginBinding
import com.owrori.android.databinding.FragmentOnboardingBinding
import com.owrori.android.ui.auth.viewmodel.OnBoardingViewModel
import org.koin.android.ext.android.inject

class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding, OnBoardingViewModel>(R.layout.fragment_onboarding) {
    override val viewModel: OnBoardingViewModel by inject()
    override fun setBindingVariables() {
        with(binding) {

        }
    }

    override fun initView() {
        initCustomOnBackPressed()
    }

    override fun initObserver() {
        with(viewModel) {

        }
    }

    private fun initCustomOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}