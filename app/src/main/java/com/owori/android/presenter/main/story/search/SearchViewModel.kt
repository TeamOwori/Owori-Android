package com.owori.android.presenter.main.story.search

import androidx.lifecycle.LiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : BaseViewModel() {
    private val _finishButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val finishButtonClicked: LiveData<Unit> = _finishButtonClicked

    fun onClickFinishButton() {
        _finishButtonClicked.call()
    }
}