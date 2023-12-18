package com.owori.android.presenter.policy

import androidx.lifecycle.LiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FamilyConnectViewModel @Inject constructor(): BaseViewModel() {

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