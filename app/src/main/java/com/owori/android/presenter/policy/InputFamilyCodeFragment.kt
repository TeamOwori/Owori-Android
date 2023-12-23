package com.owori.android.presenter.policy


import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
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
        initViewListener()
        checkGroupExist()
    }

    private fun initViewListener() {
        binding.viewpagerButton.setOnClickListener {
            navigateTo(R.id.action_inputFamilyCodeFragment_to_agreeServiceConditionFragment)
        }
        binding.imgCancel.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.inputCodeEt.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                binding.inputCodeErrorFrame.isGone = true
            } else {
                Handler(Looper.getMainLooper()).postDelayed({binding.inputCodeErrorFrame.isGone = false}, 100)
            }

        }
    }

    override fun initObserver() = with(viewModel) {
        inputCodeResult.observe(viewLifecycleOwner) {
            validateGroupCode(it)
        }
    }

    private fun checkGroupExist() {
        var startTime = System.currentTimeMillis()
        var endTime: Long
        binding.inputCodeEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                endTime = System.currentTimeMillis()
                if(endTime-startTime >= INPUT_DELAY) {
                    s?.let {
                        val code = it.toString()
                        viewModel.codeExist(code)
                    }
                }
                startTime = endTime
            }

            override fun afterTextChanged(s: Editable?) = Unit
        })
    }

    private fun validateGroupCode(exist: Boolean?) {
        when(exist) {
            null->  { return }
            true -> {
                binding.inputCodeResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue_1C86FF))
                binding.inputCodeResult.setText(R.string.input_code_success)
                binding.viewpagerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                binding.viewpagerButton.isEnabled = true
            }
            false -> {
                binding.inputCodeResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_ff3f3f))
                binding.inputCodeResult.setText(R.string.input_code_wrong)
                binding.viewpagerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_909090))
                binding.viewpagerButton.isEnabled = false
            }
        }
    }


    companion object {
        private val INPUT_DELAY = 100L
    }
}