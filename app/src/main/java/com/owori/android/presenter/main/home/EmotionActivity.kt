package com.owori.android.presenter.main.home

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.databinding.ActivityEmotionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmotionActivity : BaseActivity<ActivityEmotionBinding, EmotionViewModel>(R.layout.activity_emotion) {
    override val viewModel : EmotionViewModel by viewModels()
    override fun setBindingVariables(binding: ActivityEmotionBinding) {
        binding.vm = viewModel
    }

    override fun initView() {}
    override fun onResume() {
        super.onResume()
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow_ffeeb2)
    }

    override fun initObserver() {
        with(viewModel) {
            closeButtonClicked.observe(this@EmotionActivity) {
                finish()
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            Intent(context, EmotionActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}