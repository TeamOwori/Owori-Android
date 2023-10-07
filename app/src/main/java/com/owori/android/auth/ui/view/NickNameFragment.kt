package com.owori.android.auth.ui.view


import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.auth.ui.viewmodel.NickNameViewModel
import com.owori.android.common.navigateTo
import com.owori.android.common.ui.view.BaseFragment
import com.owori.android.databinding.FragmentNickNameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NickNameFragment: BaseFragment<FragmentNickNameBinding, NickNameViewModel>(R.layout.fragment_nick_name) {
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
            nickname.observe(viewLifecycleOwner) {
                binding.viewpagerButton.isEnabled = it.isNotEmpty()
                binding.nicknameLength.text = it.length.toString()
                if(it.isNotEmpty()) {
                    binding.viewpagerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                } else {
                    binding.viewpagerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_909090))
                }
            }

            returnLogin.observe(viewLifecycleOwner) {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
            btnNext.observe(viewLifecycleOwner) {
                navigateTo(R.id.action_nickNameFragment_to_birthDateFragment)
            }
        }
    }
}