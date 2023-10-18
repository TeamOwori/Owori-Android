package com.owori.android.presenter.main.chat

import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment :
    BaseFragment<FragmentChatBinding, ChatViewModel>(R.layout.fragment_chat) {
    override val viewModel: ChatViewModel by viewModels()
    override fun setBindingVariables() {}

    override fun initView() {}

    override fun initObserver() {}
}