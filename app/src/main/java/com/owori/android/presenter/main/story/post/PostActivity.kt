package com.owori.android.presenter.main.story.post

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.getColor
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.core.BaseDialogFragment
import com.owori.android.databinding.ActivityPostBinding
import com.owori.android.presenter.main.home.adapter.AddPhotoListAdapter
import com.owori.android.presenter.model.PhotoData
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedimagepicker.builder.TedImagePicker


@AndroidEntryPoint
class PostActivity :
    BaseActivity<ActivityPostBinding, PostViewModel>(R.layout.activity_post) {
    override val viewModel: PostViewModel by viewModels()
    private val photoListAdapter: AddPhotoListAdapter by lazy {
        AddPhotoListAdapter { id -> viewModel.onClickDeletePhotoButton(id) }
    }

    override fun setBindingVariables(binding: ActivityPostBinding) {
        binding.vm = viewModel
    }

    override fun initView() {
        initStoryEdit()
        initPhotoListAdapter()
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
            showImagePicker.observe(this@PostActivity) {
                photoList.value?.let {
                    if (it.size != MAX_PHOTO_SIZE) {
                        TedImagePicker.with(this@PostActivity)
                            .max(MAX_PHOTO_SIZE - it.size, SIZE_WARN)
                            .showCameraTile(false)
                            .startMultiImage { uris ->
                                viewModel.setPhotoList(uris.mapIndexed { index, uri ->
                                    PhotoData(index == 0, index, uri.toString())
                                })
                            }
                    } else Toast.makeText(this@PostActivity, SIZE_WARN, Toast.LENGTH_SHORT)
                }
            }
            photoList.observe(this@PostActivity) {
                Log.d("hello", "$it")
                photoListAdapter.submitList(it)
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

    private fun initPhotoListAdapter() {
        binding.addPhotoListAdapter.adapter = photoListAdapter
    }

    companion object {
        private const val MAX_PHOTO_SIZE = 10
        private const val SIZE_WARN = "최대 10장의 사진만 선택 가능합니다."
        fun startActivity(context: Context) {
            Intent(context, PostActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}