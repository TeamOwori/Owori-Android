package com.owori.android.presenter.login

import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.owori.android.core.BaseFragment
import com.owori.android.R
import com.owori.android.databinding.FragmentLoginBinding
import com.owori.android.core.navigateTo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override val viewModel: LoginViewModel by viewModels()
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
                navigateTo(R.id.action_LoginFragment_to_nickNameFragment)
            }

            callGoogleLogin.observe(viewLifecycleOwner) {
                navigateTo(R.id.action_LoginFragment_to_nickNameFragment)
            }
        }
    }

    private fun initCustomOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}