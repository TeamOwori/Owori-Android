package com.owori.android.auth.ui.view


import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.auth.ui.viewmodel.GroupViewModel
import com.owori.android.common.navigateTo
import com.owori.android.common.ui.view.BaseFragment
import com.owori.android.databinding.FragmentGroupBinding


class GroupFragment : BaseFragment<FragmentGroupBinding, GroupViewModel>(R.layout.fragment_group) {
    override val viewModel: GroupViewModel by viewModels()
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
                navigateTo(R.id.action_groupFragment_to_shareCodeFragment)
            }
        }
    }
}