package com.owrori.android.ui.auth.view

import androidx.activity.addCallback
import com.owrori.android.ui.auth.viewmodel.LoginViewModel
import com.owrori.android.ui.common.navigateTo
import com.owrori.android.ui.common.presentation.view.BaseFragment
import com.owrori.android.R
import com.owrori.android.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override val viewModel: LoginViewModel by inject()
    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
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