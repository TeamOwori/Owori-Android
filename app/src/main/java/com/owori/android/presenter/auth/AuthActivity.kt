package com.owori.android.presenter.auth

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.owori.android.core.BaseActivity
import com.owori.android.R
import com.owori.android.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel>(R.layout.activity_auth) {
    override val viewModel : AuthViewModel by viewModels()
    override fun initView() {
        initNavGraph()
    }

    override fun initObserver() {}

    override fun setBindingVariables(binding: ActivityAuthBinding) {}

    private fun initNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.findNavController()
        navController.navInflater.inflate(R.navigation.auth_navigation).apply {
            setStartDestination(R.id.SplashFragment)
        }.run { navController.setGraph(this, null) }
    }
}