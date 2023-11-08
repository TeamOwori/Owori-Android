package com.owori.android.presenter.main.home

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.databinding.ActivityNoticeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeActivity : BaseActivity<ActivityNoticeBinding, NoticeViewModel>(R.layout.activity_notice) {
    override val viewModel : NoticeViewModel by viewModels()
    override fun setBindingVariables(binding: ActivityNoticeBinding) {
        binding.vm = viewModel
    }

    override fun initView() {}
    override fun onResume() {
        super.onResume()
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow_ffeeb2)
    }

    override fun initObserver() {
        with(viewModel) {
            closeButtonClicked.observe(this@NoticeActivity) {
                finish()
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