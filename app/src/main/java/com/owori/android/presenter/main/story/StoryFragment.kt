package com.owori.android.presenter.main.story

import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.databinding.FragmentStoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoryFragment : BaseFragment<FragmentStoryBinding, StoryViewModel>(R.layout.fragment_story) {
    override val viewModel: StoryViewModel by viewModels()
    override fun setBindingVariables() {}

    override fun initObserver() {}

    override fun initView() {}
}