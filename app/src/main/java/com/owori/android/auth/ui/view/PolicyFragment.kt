package com.owori.android.auth.ui.view

import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.databinding.FragmentPolicyBinding
import com.owori.android.auth.ui.viewmodel.PolicyViewModel
import com.owori.android.common.ui.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PolicyFragment : BaseFragment<FragmentPolicyBinding, PolicyViewModel>(R.layout.fragment_policy) {
    override val viewModel: PolicyViewModel by viewModels()
    override fun setBindingVariables() {}

    override fun initView() {}

    override fun initObserver() {}
}