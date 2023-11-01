package com.owori.android.presenter.main.home

import androidx.activity.viewModels
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.databinding.ActivityEmotionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmotionActivity : BaseActivity<ActivityEmotionBinding, EmotionViewModel>(R.layout.activity_emotion) {
    override val viewModel : EmotionViewModel by viewModels()
    override fun setBindingVariables(binding: ActivityEmotionBinding) {}

    override fun initView() {}

    override fun initObserver() {}

}