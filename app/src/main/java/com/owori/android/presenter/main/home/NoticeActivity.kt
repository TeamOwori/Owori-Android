package com.owori.android.presenter.main.home

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.databinding.ActivityNoticeBinding
import com.owori.android.presenter.main.home.adapter.NoticeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeActivity : BaseActivity<ActivityNoticeBinding, NoticeViewModel>(R.layout.activity_notice) {
    override val viewModel : NoticeViewModel by viewModels()
    private val noticeAdapter: NoticeAdapter by lazy { NoticeAdapter() }
    override fun setBindingVariables(binding: ActivityNoticeBinding) {
        binding.vm = viewModel
    }

    override fun initView() {
        binding.noticeRecycler.adapter = noticeAdapter
    }
    override fun onResume() {
        super.onResume()
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
    }

    override fun initObserver() {
        with(viewModel) {
            closeButtonClicked.observe(this@NoticeActivity) {
                finish()
            }
            noticeList.observe(this@NoticeActivity) {
                noticeAdapter.submitList(it)
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            Intent(context, NoticeActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}