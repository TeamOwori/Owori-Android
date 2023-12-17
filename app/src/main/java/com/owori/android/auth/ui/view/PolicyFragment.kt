package com.owori.android.auth.ui.view

import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import com.owori.android.R
import com.owori.android.databinding.FragmentPolicyBinding
import com.owori.android.auth.ui.viewmodel.PolicyViewModel
import com.owori.android.common.ui.view.BaseFragment
import org.koin.android.ext.android.inject

class PolicyFragment :
    BaseFragment<FragmentPolicyBinding, PolicyViewModel>(R.layout.fragment_policy) {
    override val viewModel: PolicyViewModel by inject()

    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initView() {
        initTextWatcher()
        initShareCodeExplain()
    }

    override fun initObserver() {
        with(viewModel) {
            nickName.observe(viewLifecycleOwner) {
                Log.d("nickname", it)
                if(it.isEmpty()) {
                    setButtonEnableFalse()
                } else {
                    setButtonEnableTrue()
                }
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

            finishPolicy.observe(viewLifecycleOwner) {
                val bottomSheetFragment = AgreeAutoLoginFragment()
                bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
            }
            returnLogin.observe(viewLifecycleOwner) {
                Log.d("뒤로가기", "1번에서 뒤로가기 눌림")
            }
            indexBack.observe(viewLifecycleOwner) {
//                binding.viewpagerButton.isEnabled = true

                setFrameVisibility()
                setCurrentFragment()
            }
            indexForward.observe(viewLifecycleOwner) {
                binding.viewpagerButton.isEnabled = false
                setFrameVisibility()
                setCurrentFragment()
            }
            groupName.observe(viewLifecycleOwner) {
                if(it.isEmpty()) {
                    setButtonEnableFalse()
                } else {
                    setButtonEnableTrue()
                }
            }
            shareCode.observe(viewLifecycleOwner) {

            }
            groupCode.observe(viewLifecycleOwner) {
                if(it.isEmpty()) {
                    setButtonEnableFalse()
                } else {
                    setButtonEnableTrue()
                }
            }
            checkedAll.observe(viewLifecycleOwner) {
                setButtonEnableTrue()
                val bottomSheetFragment = AgreeAutoLoginFragment()
                bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
            }
            notChecked.observe(viewLifecycleOwner) {
                setButtonEnableFalse()
            }
            serviceClicked.observe(viewLifecycleOwner) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(R.string.service_page)))
                startActivity(intent)
            }
            personalInfoClicked.observe(viewLifecycleOwner) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(R.string.personal_info_page)))
                startActivity(intent)
            }
        }
    }

    private fun setCurrentFragment() {
        Log.d("currentIndex", viewModel.currentIndex.value.toString())
        Log.d("lastIndex", viewModel.lastIndex.value.toString())
        binding.indicatorLayout.visibility = View.VISIBLE
        binding.backButton.visibility = View.VISIBLE
        when (viewModel.currentIndex.value) {
            0 -> {
                binding.nicknameFrame.visibility = View.VISIBLE
                if(!viewModel.nickName.value.isNullOrEmpty()) {
                    setButtonEnableTrue()
                } else {
                    setButtonEnableFalse()
                }
            }
            1 -> {
                binding.birthdateFrame.visibility = View.VISIBLE
                val verify = viewModel.birthDate.value?.split("-")
                if(verify?.size == 3) {
                    if(verify[0].length == 4 && verify[1].length == 2 && verify[2].length == 2){
                        setButtonEnableTrue()
                    } else {
                        setButtonEnableFalse()
                    }
                }  else {
                    setButtonEnableFalse()
                }
                binding.viewpagerButton.visibility = View.VISIBLE
            }
            2 -> {
                binding.familyConnectFrame.visibility = View.VISIBLE
                binding.viewpagerButton.visibility = View.INVISIBLE
            }
            3 -> {
                binding.groupFrame.visibility = View.VISIBLE
                binding.viewpagerButton.visibility = View.VISIBLE
                if(!viewModel.groupName.value.isNullOrEmpty()) {
                    setButtonEnableTrue()
                } else {
                    setButtonEnableFalse()
                }
            }
            4 -> {
                binding.shareCodeFrame.visibility = View.VISIBLE
                binding.indicatorLayout.visibility = View.GONE
                binding.backButton.visibility = View.GONE
                binding.closeButton.visibility = View.VISIBLE
                setButtonEnableTrue()
            }
            5 -> {
                if(!viewModel.groupCode.value.isNullOrEmpty()) {
                    setButtonEnableTrue()
                } else {
                    setButtonEnableFalse()
                }
                binding.indicatorLayout.visibility = View.GONE
                binding.backButton.visibility = View.GONE
                binding.closeButton.visibility = View.VISIBLE
                binding.viewpagerButton.visibility = View.VISIBLE
                binding.inputCodeFrame.visibility = View.VISIBLE
                binding.inputCodeExplainFrame.visibility = View.VISIBLE
            }
            6 -> {
                if(viewModel.serviceChecked.value == true && viewModel.personalInfoChecked.value == true) {
                    setButtonEnableTrue()
                } else {
                    setButtonEnableFalse()
                }
                setButtonEnableFalse()
                binding.agreeServiceConditionFrame.visibility = View.VISIBLE
            }
        }
    }

    private fun initTextWatcher() {
        binding.birthdateEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var textlength = 0
                if(binding.birthdateEt.isFocusable() && !s.toString().equals("")) {
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

                    }else if(textlength == 5 && !binding.birthdateEt.text.toString().contains("-") && !binding.birthdateEt.text.toString().substring(0,4).contains('-')){
                        val date = binding.birthdateEt.text.toString().substring(0,4)+"-"+binding.birthdateEt.text.toString().substring(4)
                        binding.birthdateEt.setText(date)
                        binding.birthdateEt.setSelection(binding.birthdateEt.text.length)

                    }else if(textlength == 8 && binding.birthdateEt.text.toString().substring(7,8) != "-" && !binding.birthdateEt.text.toString().substring(0,4).contains('-')
                        && !binding.birthdateEt.text.toString().substring(5,8).contains('-') && !binding.birthdateEt.text.toString().substring(5).contains('-')){
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
    private fun initCustomOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel.onClickBackButton()
        }
    }

    private fun setFrameVisibility() {
        binding.closeButton.visibility = View.GONE
        binding.nicknameFrame.visibility = View.GONE
        binding.birthdateFrame.visibility = View.GONE
        binding.familyConnectFrame.visibility = View.GONE
        binding.groupFrame.visibility = View.GONE
        binding.shareCodeFrame.visibility = View.GONE
        binding.inputCodeFrame.visibility = View.GONE
        binding.inputCodeExplainFrame.visibility = View.GONE
        binding.agreeServiceConditionFrame.visibility = View.GONE
    }

    private fun setButtonEnableTrue() {
        binding.viewpagerButton.isEnabled = true
        binding.viewpagerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    private fun setButtonEnableFalse() {
        binding.viewpagerButton.isEnabled = false
        binding.viewpagerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_909090))
    }



    private fun initShareCodeExplain() {
        val builder = SpannableStringBuilder(binding.shareCodeExplain.text.toString())
        val color9090Span1 = ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.grey_909090))
        val color9090Span2 = ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.grey_909090))
        builder.setSpan(color9090Span1, 12, 19, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        builder.setSpan(color9090Span2, 48, 70, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        binding.shareCodeExplain.text = builder
    }

    private fun setInputGroupCodeResult(result: Int) {
        when(result) {
            0 -> {
                binding.inputCodeResult.text = resources.getString(R.string.input_code_success)
                binding.inputCodeResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue_1C86FF))
            }
            1-> {
                binding.inputCodeResult.text = resources.getString(R.string.input_code_wrong)
                binding.inputCodeResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_FF3F3F))
            }
            2 -> {
                binding.inputCodeResult.text = resources.getString(R.string.input_code_late)
                binding.inputCodeResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_FF3F3F))
            }
            else -> {
                binding.inputCodeResult.text = resources.getString(R.string.input_code_wrong)
                binding.inputCodeResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_FF3F3F))
            }
        }

    }


    private fun registerOnPageChangeCallback() {

    }

}