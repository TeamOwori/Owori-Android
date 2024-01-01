package com.owori.android.presenter.main.home.familyname

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.getColor
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.core.BaseDialogFragment
import com.owori.android.databinding.ActivityFamilyNameBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FamilyNameActivity :
    BaseActivity<ActivityFamilyNameBinding, FamilyNameViewModel>(R.layout.activity_family_name) {
    override val viewModel: FamilyNameViewModel by viewModels()

    override fun setBindingVariables(binding: ActivityFamilyNameBinding) {
        binding.vm = viewModel
    }

    override fun initView() {
        initNickNameTextViewEdit()
    }

    override fun initObserver() {
        with(viewModel) {
            cancelButtonClicked.observe(this@FamilyNameActivity) {
                BaseDialogFragment(title = getString(R.string.dialog_change_cancel_title),
                    contents = getString(R.string.dialog_change_cancel_contents),
                    positiveButtonText = getString(R.string.dialog_change_cancel_title),
                    cancelButtonText = getString(R.string.dialog_cancel),
                    onClickPositiveButton = { finish() }).show(
                    this@FamilyNameActivity.supportFragmentManager,
                    getString(R.string.dialog_change_cancel)
                )
            }
            saveButtonClicked.observe(this@FamilyNameActivity) {
                if (binding.familyGroupNameTextViewEdit.text.isEmpty()) {
                    Toast.makeText(this@FamilyNameActivity, getString(R.string.message_wrong_input), Toast.LENGTH_SHORT).show()
                } else {
                    saveFamilyGroupName(binding.familyGroupNameTextViewEdit.text.toString())
                    finish()
                }

            }
        }
    }

    override fun onBackPressed() {
        BaseDialogFragment(title = getString(R.string.dialog_change_cancel_title),
            contents = getString(R.string.dialog_change_cancel_contents),
            positiveButtonText = getString(R.string.dialog_change_cancel_title),
            cancelButtonText = getString(R.string.dialog_cancel),
            onClickPositiveButton = { finish() }).show(
            this@FamilyNameActivity.supportFragmentManager,
            getString(R.string.dialog_change_cancel)
        )
    }

    override fun onResume() {
        super.onResume()
        window.statusBarColor = getColor(this, R.color.yellow_fefaea)
    }

    private fun initNickNameTextViewEdit() {
        with(binding) {
            familyGroupNameTextViewEdit.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    familyGroupNameDivider.visibility = View.VISIBLE
                    familyGroupNameLengthTextView.visibility = View.VISIBLE
                    familyGroupNameLengthTextView.text =
                        "${familyGroupNameTextViewEdit.text.length}/10"
                    labelFamilyGroupNameWarn.visibility = View.VISIBLE
                } else {
                    familyGroupNameDivider.visibility = View.GONE
                    familyGroupNameLengthTextView.visibility = View.GONE
                    labelFamilyGroupNameWarn.visibility = View.GONE
                }
            }
            familyGroupNameTextViewEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    familyGroupNameLengthTextView.text =
                        "${familyGroupNameTextViewEdit.text.length}/10"
                    familyGroupNameLengthTextView.visibility =
                        if (familyGroupNameTextViewEdit.text.isEmpty())
                            View.GONE
                        else View.VISIBLE
                    setLabelNickNameWarn(familyGroupNameTextViewEdit.text.isNotBlank())
                }

                override fun afterTextChanged(s: Editable?) = Unit

                private fun setLabelNickNameWarn(isValid: Boolean) {
                    with(labelFamilyGroupNameWarn) {
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


    companion object {
        fun startActivity(context: Context) {
            Intent(context, FamilyNameActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}