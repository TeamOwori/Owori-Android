package com.owori.android.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import com.owori.android.common.SingleLiveEvent
import com.owori.android.common.ui.viewmodel.BaseViewModel
import javax.inject.Inject

class FamilyConnectViewModel @Inject constructor() : BaseViewModel() {

    private val _btnNext: SingleLiveEvent<Unit> = SingleLiveEvent()
    val btnNext: LiveData<Unit> = _btnNext
    private val _returnLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val returnLogin: LiveData<Unit> = _returnLogin

    fun onClickCheckButton() {
        _btnNext.call()
    }

    fun onClickBackButton() {
        _returnLogin.call()
    }
}