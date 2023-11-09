package com.owori.android.presenter.policy


import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.core.navigateTo
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
                setViewPagerButton(it)
            }

            returnLogin.observe(viewLifecycleOwner) {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
            btnNext.observe(viewLifecycleOwner) {
                navigateTo(R.id.action_nickNameFragment_to_birthDateFragment)
            }
        }
    }

    fun setViewPagerButton(nickname: String) {
        val isInputted = nickname.isNotEmpty()
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