package com.owori.android.auth.ui.view

import com.owori.android.R
import com.owori.android.databinding.FragmentPolicyBinding
import com.owori.android.auth.ui.viewmodel.PolicyViewModel
import com.owori.android.common.ui.view.BaseFragment
import org.koin.android.ext.android.inject

class PolicyFragment :
    BaseFragment<FragmentPolicyBinding, PolicyViewModel>(R.layout.fragment_policy) {
    override val viewModel: PolicyViewModel by inject()
    override fun setBindingVariables() {}

    override fun initView() {}

    override fun initObserver() {}
}