package com.owori.android.auth.ui.view


import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.auth.ui.viewmodel.FamilyConnectViewModel
import com.owori.android.common.navigateTo
import com.owori.android.common.ui.view.BaseFragment
import com.owori.android.databinding.FragmentFamilyConnectBinding


class FamilyConnectFragment : BaseFragment<FragmentFamilyConnectBinding, FamilyConnectViewModel>(R.layout.fragment_family_connect) {
    override val viewModel: FamilyConnectViewModel by viewModels()
    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initView() {

    }

    override fun initObserver() {
        with(viewModel) {
            returnLogin.observe(viewLifecycleOwner) {
                navigateTo(R.id.action_familyConnectFragment_to_inputFamilyCodeFragment)
            }
            btnNext.observe(viewLifecycleOwner) {
                navigateTo(R.id.action_familyConnectFragment_to_groupFragment)
            }
        }
    }
}