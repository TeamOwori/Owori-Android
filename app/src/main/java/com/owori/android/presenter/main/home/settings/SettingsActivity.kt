package com.owori.android.presenter.main.home.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.core.BaseDialogFragment
import com.owori.android.databinding.ActivitySettingsBinding
import com.owori.android.presenter.main.home.familyname.FamilyNameActivity
import com.owori.android.presenter.main.home.familyname.FamilyNameViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsActivity :
    BaseActivity<ActivitySettingsBinding, SettingsViewModel>(R.layout.activity_settings) {
    override val viewModel: SettingsViewModel by viewModels()

    override fun setBindingVariables(binding: ActivitySettingsBinding) {
        binding.vm = viewModel
    }

    override fun initView() {
        initAutoLoginSwitch()
    }

    override fun initObserver() {
        with(viewModel) {
            closeButtonClicked.observe(this@SettingsActivity) {
                finish()
            }
            uriToMove.observe(this@SettingsActivity) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
            logoutButtonClicked.observe(this@SettingsActivity) {
                BaseDialogFragment(title = getString(R.string.label_logout),
                    contents = getString(R.string.label_logout_contents),
                    positiveButtonText = getString(R.string.label_logout),
                    cancelButtonText = getString(R.string.dialog_cancel),
                    onClickPositiveButton = { logout() }).show(
                    this@SettingsActivity.supportFragmentManager,
                    getString(R.string.dialog_logout)
                )
            }
            withdrawalButtonClicked.observe(this@SettingsActivity) {
                BaseDialogFragment(title = getString(R.string.label_withdrawal),
                    contents = getString(R.string.label_withdrawal_contents),
                    positiveButtonText = getString(R.string.label_withdrawal),
                    cancelButtonText = getString(R.string.dialog_cancel),
                    onClickPositiveButton = { withdrawal() }).show(
                    this@SettingsActivity.supportFragmentManager,
                    getString(R.string.dialog_withdrawal)
                )
            }
            invitationButtonClicked.observe(this@SettingsActivity) {

            }
            changeFamilyNameButtonClicked.observe(this@SettingsActivity) {
                FamilyNameActivity.startActivity(this@SettingsActivity)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow_ffeeb2)
    }

    private fun initAutoLoginSwitch() {
        binding.autoLoginSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setAutoLoginState(isChecked)
        }
    }

    private fun logout() {
        viewModel.fetchLogout()
        finish()
    }

    private fun withdrawal() {
        viewModel.fetchWithdrawal()
        finish()
    }

    companion object {
        fun startActivity(context: Context) {
            Intent(context, SettingsActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}