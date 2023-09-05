package com.owori.android

import com.owori.android.auth.ui.viewmodel.AuthViewModel
import com.owori.android.auth.ui.viewmodel.LoginViewModel
import com.owori.android.auth.ui.viewmodel.OnBoardingViewModel
import com.owori.android.auth.ui.viewmodel.PolicyViewModel
import com.owori.android.auth.ui.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel() }
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel() }
    viewModel { OnBoardingViewModel() }
    viewModel { PolicyViewModel() }
}

val appModules = listOf(viewModelModule)