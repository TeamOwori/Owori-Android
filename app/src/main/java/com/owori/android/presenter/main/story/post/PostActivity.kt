package com.owori.android.presenter.main.story.post

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.getColor
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.core.BaseDialogFragment
import com.owori.android.databinding.ActivityPostBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostActivity :
    BaseActivity<ActivityPostBinding, PostViewModel>(R.layout.activity_post) {
    override val viewModel: PostViewModel by viewModels()

    override fun setBindingVariables(binding: ActivityPostBinding) {
        binding.vm = viewModel
    }

    override fun initView() {
        initStoryEdit()
    }

    override fun onResume() {
        super.onResume()
        setStatusBarColor(getColor(this, R.color.white))
    }

    override fun initObserver() {
        with(viewModel) {
            savePostButtonClicked.observe(this@PostActivity) {

            }
            cancelButtonClicked.observe(this@PostActivity) {
                BaseDialogFragment(
                    title = getString(R.string.dialog_change_cancel_title),
                    contents = getString(R.string.dialog_change_cancel_contents),
                    positiveButtonText = getString(R.string.dialog_change_cancel_title),
                    cancelButtonText = getString(R.string.dialog_cancel),
                    onClickPositiveButton = { finish() })
                    .show(
                        this@PostActivity.supportFragmentManager,
                        getString(R.string.dialog_change_cancel)
                    )
            }
        }
    }

    private fun initStoryEdit() {
        with(binding) {
            titleEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    checkPostData()
                }

                override fun afterTextChanged(s: Editable?) = Unit
            })
            storyEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    storyTextLength.text = "${storyEdit.text.length}/500"
                    checkPostData()
                }

                override fun afterTextChanged(s: Editable?) = Unit
            })

            storyEdit.setOnTouchListener { v, event ->
                if (v?.id === storyEdit.id) {
                    v.parent.requestDisallowInterceptTouchEvent(true)
                    if (event?.action == MotionEvent.ACTION_UP && MotionEvent.ACTION_MASK == MotionEvent.ACTION_UP) {
                        v.parent.requestDisallowInterceptTouchEvent(false)
                    }
                }
                false
            }
        }
    }

    private fun checkPostData() {
        with (binding) {
            if (storyEdit.text.isNotEmpty() && titleEdit.text.isNotEmpty()) {
                viewModel.setPostMode(true)
            } else {
                viewModel.setPostMode(false)
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            Intent(context, PostActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}