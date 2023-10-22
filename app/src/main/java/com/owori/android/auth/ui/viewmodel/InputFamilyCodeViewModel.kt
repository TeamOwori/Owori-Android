package com.owori.android.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import com.owori.android.common.SingleLiveEvent
import com.owori.android.common.ui.viewmodel.BaseViewModel
import javax.inject.Inject

class InputFamilyCodeViewModel @Inject constructor() : BaseViewModel() {

    private val _btnNext: SingleLiveEvent<Unit> = SingleLiveEvent()
    val btnNext: LiveData<Unit> = _btnNext

    fun onClickCheckButton() {
        _btnNext.call()
    }
}