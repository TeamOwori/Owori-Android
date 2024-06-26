package com.owori.android.presenter.main.story.detail

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.core.BaseDialogFragment
import com.owori.android.databinding.ActivityDetailBinding
import com.owori.android.presenter.main.story.adapter.DetailPhotoAdapter
import com.owori.android.presenter.main.story.post.PostActivity
import com.owori.android.presenter.model.PostData
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity :
    BaseActivity<ActivityDetailBinding, DetailViewModel>(R.layout.activity_detail) {
    override val viewModel: DetailViewModel by viewModels()
    private val familyPhotoAdapter: DetailPhotoAdapter by lazy { DetailPhotoAdapter { } }

    override fun onPostResume() {
        super.onPostResume()
        setStatusBarColor(getColor(R.color.white))
    }

    override fun initView() {}

    override fun initObserver() {
        with(viewModel) {
            finishButtonClicked.observe(this@DetailActivity) {
                finish()
            }
            deleteButtonClicked.observe(this@DetailActivity) {
                BaseDialogFragment(title = getString(R.string.dialog_delete_title),
                    contents = getString(R.string.dialog_delete_story_contents),
                    positiveButtonText = getString(R.string.dialog_delete_story_title),
                    onClickPositiveButton = {
                        deleteStory()
                        Toast.makeText(this@DetailActivity, "게시글을 삭제했어요.", Toast.LENGTH_SHORT).show()
                        this@DetailActivity.finish()
                    })
                    .show(
                        this@DetailActivity.supportFragmentManager,
                        getString(R.string.dialog_delete_story)
                    )
            }
            editButtonClicked.observe(this@DetailActivity) {
                BaseDialogFragment(title = getString(R.string.dialog_edit_story_title),
                    contents = getString(R.string.dialog_edit_story_contents),
                    positiveButtonText = getString(R.string.dialog_edit_story_title),
                    onClickPositiveButton = {
                        this@DetailActivity.finish()
                        postData.value?.let {
                            PostActivity.startActivity(this@DetailActivity, it)
                        }

                    })
                    .show(
                        this@DetailActivity.supportFragmentManager,
                        getString(R.string.dialog_edit_story)
                    )
            }
        }
    }

    override fun setBindingVariables(binding: ActivityDetailBinding) {
        with(binding) {
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