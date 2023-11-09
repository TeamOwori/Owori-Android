package com.owori.android.presenter.policy

import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.databinding.FragmentAgreeServiceConditionBinding

class AgreeServiceConditionFragment : BaseFragment<FragmentAgreeServiceConditionBinding, AgreeServiceConditionViewModel>(R.layout.fragment_agree_service_condition) {
    override val viewModel: AgreeServiceConditionViewModel by viewModels()
    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initView() {

    }

    override fun initObserver() {

    }
}