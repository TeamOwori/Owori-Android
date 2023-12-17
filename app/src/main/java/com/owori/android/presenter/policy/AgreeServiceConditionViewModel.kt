package com.owori.android.presenter.policy

import androidx.lifecycle.LiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgreeServiceConditionViewModel @Inject constructor(): BaseViewModel() {
    private val _btnNext: SingleLiveEvent<Unit> = SingleLiveEvent()
    val btnNext: LiveData<Unit> = _btnNext

    fun onClickCheckButton() {
        _btnNext.call()
    }
}