package com.owori.android.presenter.login

import android.content.ContentValues
import android.util.Log
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.databinding.FragmentLoginBinding
import com.owori.android.presenter.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override val viewModel: LoginViewModel by viewModels()
    private val userApiClient = UserApiClient()
    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(ContentValues.TAG, "Failed to Kakao Login", error)
        } else if (token != null) {
            Log.i(ContentValues.TAG, "Success Kakao Login token : ${token.accessToken}")
            MainActivity.startActivity(requireActivity())
        }
    }

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
                startKakaoLogin()
//                navigateTo(R.id.action_loginFragment_to_PolicyFragment)
            }

            callGoogleLogin.observe(viewLifecycleOwner) {
//                navigateTo(R.id.action_loginFragment_to_PolicyFragment)
                MainActivity.startActivity(requireActivity())
                requireActivity().finish()
            }
        }
    }

    private fun startKakaoLogin() {
        if (userApiClient.isKakaoTalkLoginAvailable(requireActivity().applicationContext)) {
            userApiClient.loginWithKakaoTalk(requireActivity().applicationContext) { token, error ->
                if (error != null) {
                    Log.e(ContentValues.TAG, "Failed To Kakao Login", error)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    userApiClient.loginWithKakaoAccount(requireActivity().applicationContext, callback = callback)
                } else if (token != null) {
                    Log.i(ContentValues.TAG, "Success to Kakao Login ${token.accessToken}")
                }
            }
        } else {
            userApiClient.loginWithKakaoAccount(requireActivity().applicationContext, callback = callback)
        }
    }

    private fun initCustomOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}