package com.owori.android.presenter.emotion

import android.content.Context
import android.content.Intent
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.databinding.ActivityEmotionBinding
import com.owori.android.presenter.main.home.adapter.EmotionAdapter
import com.owori.android.presenter.model.EmotionItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmotionActivity :
    BaseActivity<ActivityEmotionBinding, EmotionViewModel>(R.layout.activity_emotion) {
    override val viewModel: EmotionViewModel by viewModels()
    private val emotionListAdapter: EmotionAdapter by lazy {
        EmotionAdapter { viewModel.onClickEmotionItem(it) }
    }

    override fun setBindingVariables(binding: ActivityEmotionBinding) {
        binding.vm = viewModel
    }

    override fun initView() {
        initEmotionList()
        initEmotionRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow_ffeeb2)
    }

    override fun initObserver() =
        with(viewModel) {
            closeButtonClicked.observe(this@EmotionActivity) {
                finish()
            }
            emotionList.observe(this@EmotionActivity) {
                emotionListAdapter.submitList(it)
            }
            callClickSubmitButton.observe(this@EmotionActivity) {
                makeText(
                    this@EmotionActivity,
                    getString(R.string.message_emotion_submit),
                    LENGTH_SHORT
                ).show()
                finish()
            }
        }


    private fun initEmotionRecyclerView() {
        with(binding) {
            emotionRecyclerView.apply {
                isNestedScrollingEnabled = false
                isScrollContainer = false
                adapter = emotionListAdapter
            }
        }
    }

    private fun initEmotionList() {
        viewModel.setEmotionList(EMOTION_DRAWABLE_LIST.mapIndexed { _id, _drawable ->
            EmotionItem(
                _id,
                _drawable,
                false
            )
        })
    }

    companion object {
        private val EMOTION_DRAWABLE_LIST = listOf(
            R.drawable.emoji_excited,
            R.drawable.emoji_happy,
            R.drawable.emoji_love,
            R.drawable.emoji_kiss,
            R.drawable.emoji_surprised,
            R.drawable.emoji_cool,
            R.drawable.emoji_soso,
            R.drawable.emoji_sleepy,
            R.drawable.emoji_tired,
            R.drawable.emoji_confused,
            R.drawable.emoji_sad,
            R.drawable.emoji_very_sad,
            R.drawable.emoji_shocked,
            R.drawable.emoji_angry,
            R.drawable.emoji_very_angry,
        )

        fun startActivity(context: Context) {
            Intent(context, EmotionActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}