package com.owori.android.auth.ui.view


import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.auth.ui.viewmodel.BirthDateViewModel
import com.owori.android.common.navigateTo
import com.owori.android.common.ui.view.BaseFragment
import com.owori.android.databinding.FragmentBirthDateBinding


class BirthDateFragment : BaseFragment<FragmentBirthDateBinding, BirthDateViewModel>(R.layout.fragment_birth_date) {
    override val viewModel: BirthDateViewModel by viewModels()
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
                navigateTo(R.id.action_birthDateFragment_to_familyConnectFragment)
            }
        }
    }
}