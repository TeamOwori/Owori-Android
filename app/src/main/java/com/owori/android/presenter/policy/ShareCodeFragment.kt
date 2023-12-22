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
        initTextEmphasis()
        initClickListener()
    }

    private fun initClickListener() {
        binding.viewpagerButton.setOnClickListener {
            navigateTo(R.id.action_shareCodeFragment_to_agreeServiceConditionFragment)
        }
        binding.shareCodeButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
                type = SHARE_CODE_TYPE
                putExtra(Intent.EXTRA_TEXT, binding.shareCodeText.text.toString())
            }
            startActivity(Intent.createChooser(intent, "아무 제목"))
        }
        binding.imgCancel.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initTextEmphasis() {
        val builder = SpannableStringBuilder(binding.shareCodeExplain.text.toString())
        val color9090Span1 = ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.grey_909090))
        val color9090Span2 = ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.grey_909090))
        builder.setSpan(color9090Span1, FIST_EMPHASIS_PART_START, FIST_EMPHASIS_PART_END, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        builder.setSpan(color9090Span2, LAST_EMPHASIS_PART_START, LAST_EMPHASIS_PART_END, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        binding.shareCodeExplain.text = builder
    }

    override fun initObserver() = Unit

    companion object {
        private const val FIST_EMPHASIS_PART_START = 12
        private const val FIST_EMPHASIS_PART_END = 19
        private const val LAST_EMPHASIS_PART_START = 48
        private const val LAST_EMPHASIS_PART_END =70
        private const val SHARE_CODE_TYPE = "text/plain"
    }
}