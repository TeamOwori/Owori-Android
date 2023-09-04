package com.owori.android.auth.ui.view

import androidx.activity.addCallback
import com.owori.android.auth.ui.viewmodel.LoginViewModel
import com.owori.android.common.ui.view.BaseFragment
import com.owori.android.R
import com.owori.android.databinding.FragmentLoginBinding
import com.owori.android.common.navigateTo
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
            // TODO : PolicyFragment를 위해 작성한 부분이라 수정 필요.
            callKakaoLogin.observe(viewLifecycleOwner) {
                navigateTo(R.id.action_loginFragment_to_PolicyFragment)
            }

            callGoogleLogin.observe(viewLifecycleOwner) {
                navigateTo(R.id.action_loginFragment_to_PolicyFragment)
            }
        }
    }

    private fun initCustomOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}