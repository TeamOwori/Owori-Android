package com.owori.android.presenter.policy


import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.core.navigateTo
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
            groupname.observe(viewLifecycleOwner) {
                setViewPagerButton(it)
            }
            btnNext.observe(viewLifecycleOwner) {
                navigateTo(R.id.action_groupFragment_to_shareCodeFragment)
            }
            btnBack.observe(viewLifecycleOwner) {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun setViewPagerButton(groupname: String) {
        val isInputted = groupname.isNotEmpty()
        with(binding.viewpagerButton) {
            isEnabled = isInputted
            if(isInputted) {
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            } else {
                setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_909090))
            }
        }
    }
}