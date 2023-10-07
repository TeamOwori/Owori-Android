package com.owori.android.auth.ui.view


import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.auth.ui.viewmodel.NickNameViewModel
import com.owori.android.common.navigateTo
import com.owori.android.common.ui.view.BaseFragment
import com.owori.android.databinding.FragmentShareCodeBinding


class ShareCodeFragment : BaseFragment<FragmentShareCodeBinding, NickNameViewModel>(R.layout.fragment_share_code) {
    override val viewModel: NickNameViewModel by viewModels()
    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initView() {

    }

    override fun initObserver() {
        with(viewModel) {
            btnNext.observe(viewLifecycleOwner) {
                navigateTo(R.id.action_shareCodeFragment_to_agreeServiceConditionFragment)
            }
        }
    }
}