package com.owori.android.presenter.policy


import android.content.Intent
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.core.navigateTo
import com.owori.android.databinding.FragmentShareCodeBinding


class ShareCodeFragment : BaseFragment<FragmentShareCodeBinding, ShareCodeViewModel>(R.layout.fragment_share_code) {
    override val viewModel: ShareCodeViewModel by viewModels()
    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initView() {
        val builder = SpannableStringBuilder(binding.shareCodeExplain.text.toString())
        val color9090Span1 = ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.grey_909090))
        val color9090Span2 = ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.grey_909090))
        builder.setSpan(color9090Span1, 12, 19, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        builder.setSpan(color9090Span2, 48, 70, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        binding.shareCodeExplain.text = builder
    }

    override fun initObserver() {
        with(viewModel) {
            btnNext.observe(viewLifecycleOwner) {
                navigateTo(R.id.action_shareCodeFragment_to_agreeServiceConditionFragment)
            }
            btnShare.observe(viewLifecycleOwner) {
                val intent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, binding.shareCodeText.text.toString())
                }
                startActivity(Intent.createChooser(intent, "제목"))
            }
            btnBack.observe(viewLifecycleOwner) {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}