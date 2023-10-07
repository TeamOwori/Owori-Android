package com.owori.android.auth.ui.view

import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.auth.ui.viewmodel.NickNameViewModel
import com.owori.android.common.ui.view.BaseFragment
import com.owori.android.databinding.FragmentAgreeServiceConditionBinding

class AgreeServiceConditionFragment : BaseFragment<FragmentAgreeServiceConditionBinding, NickNameViewModel>(R.layout.fragment_agree_service_condition) {
    override val viewModel: NickNameViewModel by viewModels()
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