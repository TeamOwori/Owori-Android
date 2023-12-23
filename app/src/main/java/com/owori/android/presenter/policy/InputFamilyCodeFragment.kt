package com.owori.android.presenter.policy


import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.core.navigateTo
import com.owori.android.databinding.FragmentInputFamilyCodeBinding


class InputFamilyCodeFragment : BaseFragment<FragmentInputFamilyCodeBinding, InputFamilyCodeViewModel>(R.layout.fragment_input_family_code) {
    override val viewModel: InputFamilyCodeViewModel by viewModels()
    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initView() {
        initClickListener()
    }

    private fun initClickListener() {
        binding.viewpagerButton.setOnClickListener {
            navigateTo(R.id.action_inputFamilyCodeFragment_to_agreeServiceConditionFragment)
        }
        binding.imgCancel.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun initObserver() = with(viewModel) {
        inputCode.observe(viewLifecycleOwner) {
            setViewPagerButton(it)
        }
    }

    private fun setViewPagerButton(code: String) {
        val isInputted = code.isNotEmpty()
        with(binding.viewpagerButton) {
            isEnabled = isInputted
            if(isInputted) {
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            } else {
                setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_909090))
            }
        }
    }

    private fun checkGroupExist(code: String) {
//        if(code) {
//            binding.inputCodeResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue_1C86FF))
//            binding.inputCodeResult.setText(R.string.input_code_success)
//            return
//        }
//        binding.inputCodeResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_ff3f3f))
//        binding.inputCodeResult.setText(R.string.input_code_wrong)
    }

}