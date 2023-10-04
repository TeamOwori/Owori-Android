package com.owori.android.auth.ui.view

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.addCallback
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import com.owori.android.R
import com.owori.android.auth.ui.viewmodel.LoginViewModel
import com.owori.android.common.navigateTo
import com.owori.android.common.ui.view.BaseFragment
import com.owori.android.databinding.FragmentLoginBinding
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
                navigateTo(R.id.action_loginFragment_to_PolicyFragment)
            }

            callGoogleLogin.observe(viewLifecycleOwner) {
                val request = GetSignInIntentRequest.builder()
                    .setServerClientId(getString(R.string.firebase_web_client_id))
                    .build()

                Identity.getSignInClient(requireActivity())
                    .getSignInIntent(request)
                    .addOnSuccessListener { result ->
                        val intentSenderRequest = IntentSenderRequest.Builder(result).build()
                        resultLauncher.launch(intentSenderRequest)
                        Log.d(TAG, "GoogleLogin launched...")
                    }.addOnFailureListener { e ->
                        Log.d(TAG, "preparing intent failed: ${e.message}")
                    }
            }
        }
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
            result: ActivityResult ->
        try {
            val credential = Identity.getSignInClient(requireActivity()).getSignInCredentialFromIntent(result.data)
            val idToken = credential.googleIdToken
            when {
                idToken != null -> {
                    // Got an ID token from Google. Use it to authenticate
                    Log.d(TAG, "Got ID token. $idToken")
                    navigateTo(R.id.action_loginFragment_to_PolicyFragment)
                }
                else -> {
                    Log.d(TAG, "No ID token!")
                }
            }
        } catch (e: ApiException) {
            Log.d(TAG, "google login failed : $e")
        }
    }

    private fun initCustomOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }


}