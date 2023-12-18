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
        initClickListener()
    }

    override fun initObserver() = with(viewModel) {
        birthDate.observe(viewLifecycleOwner) {
            val verify = it.split(HYPHEN)
            if(verify.size == VERIFY_BIRTHDATE_PARTITION) {
                if(verify[BIRTHDATE_YEAR_PART].length == YEAR_LENGTH && verify[BIRTHDATE_MONTH_PART].length == MONTH_LENGTH &&
                    verify[BIRTHDATE_DAY_PART].length == DAY_LENGTH) {
                    setButtonEnableTrue()
                } else {
                    setButtonEnableFalse()
                }
            }  else {
                setButtonEnableFalse()
            }
        }
    }
    private fun initTextWatcher() {
        binding.birthdateEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var textlength = 0
                if(binding.birthdateEt.isFocusable && s.toString().isNotBlank()) {
                    try{
                        textlength = binding.birthdateEt.text.toString().length
                    }catch (e: NumberFormatException){
                        e.printStackTrace()
                        return
                    }
                    if (textlength == YEAR_LENGTH && before != TEXT_BEFORE) {
                        val date = binding.birthdateEt.text.toString()+HYPHEN
                        binding.birthdateEt.setText(date)
                        binding.birthdateEt.setSelection(binding.birthdateEt.text.length)

                    }else if (textlength == BIRTH_DATE_CONTAIN_MONTH_LENGTH && before != TEXT_BEFORE){
                        val date = binding.birthdateEt.text.toString()+HYPHEN
                        binding.birthdateEt.setText(date)
                        binding.birthdateEt.setSelection(binding.birthdateEt.text.length)

                    }else if(textlength == YEAR_CONTAIN_HYPHEN_LENGTH && !binding.birthdateEt.text.toString().contains(HYPHEN) &&
                        binding.birthdateEt.text.toString().substring(BIRTHDATE_START, YEAR_LENGTH).contains(HYPHEN).not()){
                        val date = binding.birthdateEt.text.toString().substring(BIRTHDATE_START, YEAR_LENGTH) +
                                HYPHEN + binding.birthdateEt.text.toString().substring(YEAR_LENGTH)
                        binding.birthdateEt.setText(date)
                        binding.birthdateEt.setSelection(binding.birthdateEt.text.length)

                    }else if(textlength == MONTH_CONTAIN_HYPHEN_LENGTH &&
                        binding.birthdateEt.text.toString().substring(BIRTH_DATE_CONTAIN_MONTH_LENGTH, MONTH_CONTAIN_HYPHEN_LENGTH) != HYPHEN &&
                        binding.birthdateEt.text.toString().substring(BIRTHDATE_START,YEAR_LENGTH).contains(HYPHEN).not() &&
                        binding.birthdateEt.text.toString().substring(YEAR_CONTAIN_HYPHEN_LENGTH,MONTH_CONTAIN_HYPHEN_LENGTH).contains(HYPHEN).not() &&
                        binding.birthdateEt.text.toString().substring(YEAR_CONTAIN_HYPHEN_LENGTH).contains(HYPHEN).not()){
                        val date = binding.birthdateEt.text.toString().substring(BIRTHDATE_START,BIRTH_DATE_CONTAIN_MONTH_LENGTH) +
                                HYPHEN+binding.birthdateEt.text.toString().substring(BIRTH_DATE_CONTAIN_MONTH_LENGTH)
                        binding.birthdateEt.setText(date)
                        binding.birthdateEt.setSelection(binding.birthdateEt.text.length)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) = Unit
        })
    }

    private fun initClickListener() {
        binding.viewpagerButton.setOnClickListener {
            navigateTo(R.id.action_birthDateFragment_to_familyConnectFragment)
        }
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
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

    companion object {
        private const val TEXT_BEFORE = 1
        private const val BIRTHDATE_START = 0
        private const val YEAR_LENGTH = 4
        private const val BIRTH_DATE_CONTAIN_MONTH_LENGTH = 7
        private const val MONTH_CONTAIN_HYPHEN_LENGTH = 8
        private const val YEAR_CONTAIN_HYPHEN_LENGTH = 5
        private const val VERIFY_BIRTHDATE_PARTITION = 3
        private const val BIRTHDATE_YEAR_PART = 0
        private const val BIRTHDATE_MONTH_PART = 1
        private const val BIRTHDATE_DAY_PART = 2
        private const val MONTH_LENGTH = 2
        private const val DAY_LENGTH = 2
        private const val HYPHEN = "-"
    }
}