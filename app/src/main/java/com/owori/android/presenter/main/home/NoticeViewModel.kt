package com.owori.android.presenter.main.home

import androidx.lifecycle.LiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor() : BaseViewModel() {
    private val _closeButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val closeButtonClicked: LiveData<Unit> = _closeButtonClicked

    fun onClickCloseButton() {
        _closeButtonClicked.call()
    }

}