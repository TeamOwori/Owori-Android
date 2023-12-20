package com.owori.android.presenter.mypage

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.databinding.ActivityMyPageBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyPageActivity :
    BaseActivity<ActivityMyPageBinding, MyPageViewModel>(R.layout.activity_my_page) {
    override val viewModel: MyPageViewModel by viewModels()

    override fun setBindingVariables(binding: ActivityMyPageBinding) {
        binding.vm = viewModel
    }

    override fun initView() {
        initNickNameTextViewEdit()
        initBirthTextViewEdit()
    }

    override fun onResume() {
        super.onResume()
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow_ffeeb2)
    }

    override fun initObserver() {
        with(viewModel) {
            closeButtonClicked.observe(this@MyPageActivity) {
                finish()
            }
            editButtonClicked.observe(this@MyPageActivity) {

            }
            settingsButtonClicked.observe(this@MyPageActivity) {

            }
            saveButtonClicked.observe(this@MyPageActivity) {
                with (binding) {
                    saveMyData(nicknameTextViewEdit.text.toString(), birthTextViewEdit.text.toString())
                    nicknameTextViewEdit.text.clear()
                    birthTextViewEdit.text.clear()
                }
            }
        }
    }

    private fun initNickNameTextViewEdit() {
        with(binding) {
            nicknameTextViewEdit.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    nicknameDivider.visibility = VISIBLE
                    nicknameLengthTextView.visibility = VISIBLE
                    nicknameLengthTextView.text = "${nicknameTextViewEdit.text.length}/7"
                }
                else {
                    nicknameDivider.visibility = GONE
                    nicknameLengthTextView.visibility = GONE
                }
            }
            nicknameTextViewEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    nicknameLengthTextView.text = "${nicknameTextViewEdit.text.length}/7"
                }
                override fun afterTextChanged(s: Editable?) = Unit
            })
        }
    }

    private fun initBirthTextViewEdit() {
        with(binding) {
            birthTextViewEdit.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    birthDivider.visibility = VISIBLE
                }
                else {
                    birthDivider.visibility = GONE
                }
            }

            birthTextViewEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    with(birthTextViewEdit) {
                        if (isFocusable && s.toString() != "") {
                            if (text.length == 4 && before != 1) {
                                setText("$text-")
                                setSelection(text.length)
                            } else if (text.length == 7 && before != 1) {
                                setText("$text-")
                                setSelection(text.length)
                            } else if (text.length == 5 && text.substring(4, 5) != "-") {
                                setText(text.substring(0, 4) + "-" + text.substring(4))
                                setSelection(text.length)
                            } else if (text.length == 8 && text.substring(7, 8) != "-") {
                                setText(text.substring(0, 7) + "-" + text.substring(7))
                                setSelection(text.length)
                            }
                        }
                    }
                }
                override fun afterTextChanged(s: Editable?) = Unit
            })
        }
    }

    companion object {
        fun startActivity(context: Context) {
            Intent(context, MyPageActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}