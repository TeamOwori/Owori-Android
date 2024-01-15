package com.owori.android.presenter.main.story.search

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.getColor
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity :
    BaseActivity<ActivitySearchBinding, SearchViewModel>(R.layout.activity_search) {
    override val viewModel: SearchViewModel by viewModels()

    override fun setBindingVariables(binding: ActivitySearchBinding) {
        binding.vm = viewModel
    }

    override fun initView() {}

    override fun onResume() {
        super.onResume()
        setStatusBarColor(getColor(this, R.color.white))
    }

    override fun initObserver() {
        with(viewModel) {
            finishButtonClicked.observe(this@SearchActivity) {
                finish()
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            Intent(context, SearchActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}