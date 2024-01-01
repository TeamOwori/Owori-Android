package com.owori.android.presenter.main.home.mypage

import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.databinding.ActivityMyPageBinding
import com.owori.android.presenter.main.home.settings.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initObserver() {
        with(viewModel) {
            closeButtonClicked.observe(this@MyPageActivity) {
                finish()
            }
            editButtonClicked.observe(this@MyPageActivity) {

            }
            settingsButtonClicked.observe(this@MyPageActivity) {
                SettingsActivity.startActivity(this@MyPageActivity)
            }
            saveButtonClicked.observe(this@MyPageActivity) {
                with (binding) {
                    if (labelBirthWarn.currentTextColor == getColor(R.color.blue_1c86ff) && labelNicknameWarn.currentTextColor == getColor(R.color.blue_1c86ff) && checkDate(birthTextViewEdit.text.toString())) {
                        saveMyData(
                            nicknameTextViewEdit.text.toString(),
                            birthTextViewEdit.text.toString()
                        )
                        nicknameTextViewEdit.text.clear()
                        birthTextViewEdit.text.clear()
                    } else {
                        Toast.makeText(this@MyPageActivity, getString(R.string.message_wrong_input), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkDate(dateString: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return try {
            LocalDate.parse(dateString, formatter).isBefore(LocalDate.now())
        } catch (e: DateTimeParseException) {
            false
        }
    }

    private fun initNickNameTextViewEdit() {
        with(binding) {
            nicknameTextViewEdit.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    nicknameDivider.visibility = VISIBLE
                    nicknameLengthTextView.visibility = VISIBLE
                    nicknameLengthTextView.text = "${nicknameTextViewEdit.text.length}/7"
                    labelNicknameWarn.visibility = VISIBLE
                }
                else {
                    nicknameDivider.visibility = GONE
                    nicknameLengthTextView.visibility = GONE
                    labelNicknameWarn.visibility = GONE
                }
            }
            nicknameTextViewEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    nicknameLengthTextView.text = "${nicknameTextViewEdit.text.length}/7"
                    setLabelNickNameWarn(nicknameTextViewEdit.text.isNotBlank())
                }
                override fun afterTextChanged(s: Editable?) = Unit

                private fun setLabelNickNameWarn(isValid: Boolean) {
                    with (labelNicknameWarn) {
                        text = if (isValid) {
                            setTextColor(getColor(R.color.blue_1c86ff))
                            getString(R.string.label_correct_nickname)
                        } else {
                            setTextColor(getColor(R.color.red_ff3f3f))
                            getString(R.string.label_incorrect_nickname)
                        }
                    }
                }
            })
        }
    }

    private fun initBirthTextViewEdit() {
        with(binding) {
            birthTextViewEdit.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    birthDivider.visibility = VISIBLE
                    labelBirthWarn.visibility = VISIBLE
                }
                else {
                    birthDivider.visibility = GONE
                    labelBirthWarn.visibility = GONE
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
                                setLabelBirthWarn(false)
                            } else if (text.length == 7 && before != 1) {
                                setText("$text-")
                                setSelection(text.length)
                                labelBirthWarn.setTextColor(getColor(R.color.red_ff3f3f))
                                labelBirthWarn.text = getString(R.string.label_incorrect_birth)
                            } else if (text.length == 5 && text.substring(4, 5) != "-") {
                                setText(text.substring(0, 4) + "-" + text.substring(4))
                                setSelection(text.length)
                            } else if (text.length == 8 && text.substring(7, 8) != "-") {
                                setText(text.substring(0, 7) + "-" + text.substring(7))
                                setSelection(text.length)
                            }
                            setLabelBirthWarn(text.length == 10)
                        }
                    }
                }
                override fun afterTextChanged(s: Editable?) = Unit

                private fun setLabelBirthWarn(isValid: Boolean) {
                    with (labelBirthWarn) {
                        if (isValid) {
                            setTextColor(getColor(R.color.blue_1c86ff))
                            text = getString(R.string.label_correct_birth)
                        } else {
                            setTextColor(getColor(R.color.red_ff3f3f))
                            text = getString(R.string.label_incorrect_birth)
                        }
                    }
                }
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