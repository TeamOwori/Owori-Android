package com.owori.android.presenter.main.home.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : BaseViewModel() {
    private val _familyGroupName: MutableLiveData<String> = MutableLiveData()
    val familyGroupName: LiveData<String> = _familyGroupName

    private val _isAutoLogin: MutableLiveData<Boolean> = MutableLiveData(true)
    val isAutoLogin: LiveData<Boolean> = _isAutoLogin

    private val _closeButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val closeButtonClicked: LiveData<Unit> = _closeButtonClicked

    private val _changeFamilyNameButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val changeFamilyNameButtonClicked: LiveData<Unit> = _changeFamilyNameButtonClicked

    private val _invitationButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val invitationButtonClicked: LiveData<Unit> = _invitationButtonClicked

    private val _logoutButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val logoutButtonClicked: LiveData<Unit> = _logoutButtonClicked

    private val _withdrawalButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val withdrawalButtonClicked: LiveData<Unit> = _withdrawalButtonClicked

    private val _uriToMove: MutableLiveData<String> = MutableLiveData()
    val uriToMove: LiveData<String> = _uriToMove

    init {
        initFamilyGroupName()
    }

    fun onClickCloseButton() {
        _closeButtonClicked.call()
    }

    fun onClickChangeFamilyNameButton() {
        _changeFamilyNameButtonClicked.call()
    }

    fun onClickInvitationButton() {
        _invitationButtonClicked.call()
    }

    fun onClickUri(uri: String) {
        _uriToMove.value = uri
    }

    fun onClickLogoutButton() {
        _logoutButtonClicked.call()
    }

    fun onClickWithdrawalButton() {
        _withdrawalButtonClicked.call()
    }

    fun setAutoLoginState(isChecked: Boolean) {
        _isAutoLogin.value = isChecked
        // TODO : 자동로그인 API
    }

    fun fetchLogout() {
        // TODO 로그아웃 API 연동
    }

    fun fetchWithdrawal() {
        // TODO 회원탈퇴 API 연동
    }

    private fun initFamilyGroupName() {
        _familyGroupName.value = "우당탕탕 우리 가족 🥰"
    }

    companion object {
        // TODO : 링크 수정 필요
        const val URI_FAQ = "https://linktr.ee/owori?utm_source=linktree_profile_share&ltsid=fb1c77c0-3548-4edf-adc9-a485a1ec867f"
        const val URI_FEEDBACK = "https://linktr.ee/owori?utm_source=linktree_profile_share&ltsid=fb1c77c0-3548-4edf-adc9-a485a1ec867f"
        const val URI_AGREEMENT = "https://linktr.ee/owori?utm_source=linktree_profile_share&ltsid=fb1c77c0-3548-4edf-adc9-a485a1ec867f"
        const val URI_PRIVACY_POLICY = "https://linktr.ee/owori?utm_source=linktree_profile_share&ltsid=fb1c77c0-3548-4edf-adc9-a485a1ec867f"
        const val URI_OPEN_SOURCE_LICENSE = "https://linktr.ee/owori?utm_source=linktree_profile_share&ltsid=fb1c77c0-3548-4edf-adc9-a485a1ec867f"
        const val URI_INSTAGRAM = "https://www.instagram.com/owori_official?igsh=ZjI0YjNmOWM5OA=="
    }
}