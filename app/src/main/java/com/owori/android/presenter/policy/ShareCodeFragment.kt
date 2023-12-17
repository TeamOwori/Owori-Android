package com.owori.android.presenter.policy


import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.core.navigateTo
import com.owori.android.databinding.FragmentShareCodeBinding


class ShareCodeFragment : BaseFragment<FragmentShareCodeBinding, ShareCodeViewModel>(R.layout.fragment_share_code) {
    override val viewModel: ShareCodeViewModel by viewModels()
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