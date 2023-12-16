package com.owori.android.presenter.mypage

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.databinding.ActivityMyPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageActivity :
    BaseActivity<ActivityMyPageBinding, MyPageViewModel>(R.layout.activity_my_page) {
    override val viewModel: MyPageViewModel by viewModels()

    override fun setBindingVariables(binding: ActivityMyPageBinding) {
        binding.vm = viewModel
    }

    override fun initView() {}

    override fun onResume() {
        super.onResume()
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow_ffeeb2)
    }

    override fun initObserver() {
        with(viewModel) {
            closeButtonClicked.observe(this@MyPageActivity) {
                finish()
            }
            editButtonClicked.observe(this@MyPageActivity) {

            }
            settingsButtonClicked.observe(this@MyPageActivity) {

            }
            saveButtonClicked.observe(this@MyPageActivity) {

            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            Intent(context, MyPageActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}