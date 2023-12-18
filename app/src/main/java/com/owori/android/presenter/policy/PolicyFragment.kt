package com.owori.android.presenter.policy

import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.databinding.FragmentPolicyBinding
import com.owori.android.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PolicyFragment : BaseFragment<FragmentPolicyBinding, PolicyViewModel>(R.layout.fragment_policy) {
    override val viewModel: PolicyViewModel by viewModels()
    override fun setBindingVariables() {}

    override fun initView() {}

    override fun initObserver() {}
}