package com.owori.android.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.common.SingleLiveEvent
import com.owori.android.common.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NickNameViewModel @Inject constructor() : BaseViewModel() {

    val _nickname: MutableLiveData<String> = MutableLiveData("")
    val nickname: LiveData<String> = _nickname

    private val _returnLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val returnLogin: LiveData<Unit> = _returnLogin
    private val _btnNext: SingleLiveEvent<Unit> = SingleLiveEvent()
    val btnNext: LiveData<Unit> = _btnNext

    fun onClickCheckButton() {
        _btnNext.call()
    }

    fun onClickBackButton() {
        _returnLogin.call()
    }
}