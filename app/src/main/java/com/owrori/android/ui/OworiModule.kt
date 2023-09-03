package com.owrori.android

import com.owrori.android.ui.auth.viewmodel.AuthViewModel
import com.owrori.android.ui.auth.viewmodel.LoginViewModel
import com.owrori.android.ui.auth.viewmodel.OnBoardingViewModel
import com.owrori.android.ui.auth.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel() }
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel() }
    viewModel { OnBoardingViewModel() }
}

val appModules = listOf(viewModelModule)