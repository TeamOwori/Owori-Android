package com.owori.android.presenter.policy

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.widget.Button
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.databinding.FragmentAgreeServiceConditionBinding

class AgreeServiceConditionFragment : BaseFragment<FragmentAgreeServiceConditionBinding, AgreeServiceConditionViewModel>(R.layout.fragment_agree_service_condition) {
    override val viewModel: AgreeServiceConditionViewModel by viewModels()
    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initView() {
        initViewListener()
    }

    private fun initViewListener() {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.serviceBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(R.string.service_page)))
            startActivity(intent)
        }
        binding.personalInfoBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(R.string.personal_info_page)))
            startActivity(intent)
        }
        binding.personalInfoCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            setCheckButtonColor(binding.personalInfoCheckbox, isChecked)
            validateCheckAll()
        }
        binding.serviceCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            setCheckButtonColor(binding.serviceCheckbox, isChecked)
            validateCheckAll()
        }
        binding.viewpagerButton.setOnClickListener {
            val bottomSheetFragment = AgreeAutoLoginFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
    }

    private fun setCheckButtonColor(checkBox: CheckBox, isChecked: Boolean) {
        with(checkBox) {
            buttonTintList = if (isChecked) {
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.owori_red))
            } else {
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.grey_c6c6c6))
            }
        }
    }

    private fun validateCheckAll() {
        if(binding.serviceCheckbox.isChecked && binding.personalInfoCheckbox.isChecked) {
            binding.viewpagerButton.isEnabled = true
            binding.viewpagerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        } else {
            binding.viewpagerButton.isEnabled = false
            binding.viewpagerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_909090))
        }
    }

    override fun initObserver() {

    }
}