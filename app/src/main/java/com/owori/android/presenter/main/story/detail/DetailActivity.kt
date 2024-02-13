package com.owori.android.presenter.main.story.detail

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.databinding.ActivityDetailBinding
import com.owori.android.presenter.model.PostData
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(R.layout.activity_detail) {
    override val viewModel: DetailViewModel by viewModels()

    override fun onPostResume() {
        super.onPostResume()
        setStatusBarColor(getColor(R.color.white))
    }

    override fun initView() {}

    override fun initObserver() {}

    override fun setBindingVariables(binding: ActivityDetailBinding) {
        with (binding) {
            vm = viewModel
        }
    }


    companion object {
        fun startActivity(context: Context, postData: PostData) {
            Intent(context, DetailActivity::class.java).apply {
                putExtras(bundleOf("postData" to postData))
                context.startActivity(this)
            }
        }
    }
}