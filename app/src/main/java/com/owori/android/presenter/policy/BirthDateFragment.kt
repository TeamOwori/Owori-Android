package com.owori.android.presenter.policy


import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.core.navigateTo
import com.owori.android.databinding.FragmentBirthDateBinding


class BirthDateFragment : BaseFragment<FragmentBirthDateBinding, BirthDateViewModel>(R.layout.fragment_birth_date) {
    override val viewModel: BirthDateViewModel by viewModels()
    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initView() {
        initTextWatcher()
    }

    override fun initObserver() {
        with(viewModel) {
            btnNext.observe(viewLifecycleOwner) {
                navigateTo(R.id.action_birthDateFragment_to_familyConnectFragment)
            }

            birthDate.observe(viewLifecycleOwner) {
                val verify = it.split("-")
                if(verify.size == 3) {
                    if(verify[0].length == 4 && verify[1].length == 2 && verify[2].length == 2) {
                        setButtonEnableTrue()
                    } else {
                        setButtonEnableFalse()
                    }
                }  else {
                    setButtonEnableFalse()
                }
            }

            btnBack.observe(viewLifecycleOwner) {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun initTextWatcher() {
        binding.birthdateEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var textlength = 0
                if(binding.birthdateEt.isFocusable && s.toString() != "") {
                    try{
                        textlength = binding.birthdateEt.text.toString().length
                    }catch (e: NumberFormatException){
                        e.printStackTrace()
                        return
                    }
                    if (textlength == 4 && before != 1) {
                        val date = binding.birthdateEt.text.toString()+"-"
                        binding.birthdateEt.setText(date)
                        binding.birthdateEt.setSelection(binding.birthdateEt.text.length)

                    }else if (textlength == 7&& before != 1){
                        val date = binding.birthdateEt.text.toString()+"-"
                        binding.birthdateEt.setText(date)
                        binding.birthdateEt.setSelection(binding.birthdateEt.text.length)

                    }else if(textlength == 5 && !binding.birthdateEt.text.toString().contains("-") &&
                        !binding.birthdateEt.text.toString().substring(0,4).contains('-')){
                        val date = binding.birthdateEt.text.toString().substring(0,4)+"-"+binding.birthdateEt.text.toString().substring(4)
                        binding.birthdateEt.setText(date)
                        binding.birthdateEt.setSelection(binding.birthdateEt.text.length)

                    }else if(textlength == 8 && binding.birthdateEt.text.toString().substring(7,8) != "-" &&
                        !binding.birthdateEt.text.toString().substring(0,4).contains('-') &&
                        !binding.birthdateEt.text.toString().substring(5,8).contains('-') &&
                        !binding.birthdateEt.text.toString().substring(5).contains('-')){
                        val date = binding.birthdateEt.text.toString().substring(0,7)+"-"+binding.birthdateEt.text.toString().substring(7)
                        binding.birthdateEt.setText(date)
                        binding.birthdateEt.setSelection(binding.birthdateEt.text.length)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun setButtonEnableTrue() {
        with(binding.viewpagerButton) {
            isEnabled = true
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
    }

    private fun setButtonEnableFalse() {
        with(binding.viewpagerButton) {
            isEnabled = false
            setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_909090))
        }
    }
}