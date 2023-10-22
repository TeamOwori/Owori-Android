package com.owori.android.auth.ui.view


import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.auth.ui.viewmodel.InputFamilyCodeViewModel
import com.owori.android.common.navigateTo
import com.owori.android.common.ui.view.BaseFragment
import com.owori.android.databinding.FragmentInputFamilyCodeBinding


class InputFamilyCodeFragment : BaseFragment<FragmentInputFamilyCodeBinding, InputFamilyCodeViewModel>(R.layout.fragment_input_family_code) {
    override val viewModel: InputFamilyCodeViewModel by viewModels()
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
                navigateTo(R.id.action_inputFamilyCodeFragment_to_agreeServiceConditionFragment)
            }
        }
    }
}