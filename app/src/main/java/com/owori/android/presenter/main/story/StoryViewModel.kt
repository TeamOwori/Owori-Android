package com.owori.android.presenter.main.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.model.Filter.RECENT
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor() : BaseViewModel() {
    private val _searchButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val searchButtonClicked: LiveData<Unit> = _searchButtonClicked

    private val _postStoryButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val postStoryButtonClicked: LiveData<Unit> = _postStoryButtonClicked

    private val _filterLayoutClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val filterLayoutClicked: LiveData<Unit> = _filterLayoutClicked

    private val _currentStoryFilter: MutableLiveData<String> = MutableLiveData(RECENT.label)
    val currentStoryFilter: LiveData<String> = _currentStoryFilter

    private val _isListMode: MutableLiveData<Boolean> = MutableLiveData(true)
    val isListMode: LiveData<Boolean> = _isListMode

    fun onClickSearchButton() {
        _searchButtonClicked.call()
    }

    fun onClickListViewMode() {
        if (_isListMode.value != true) {
            _isListMode.value = true
        }
    }

    fun onClickCardViewMode() {
        if (_isListMode.value == true) {
            _isListMode.value = false
        }
    }

    fun onClickPostStoryButton() {
        _postStoryButtonClicked.call()
    }

    fun onClickFilterLayout() {
        _filterLayoutClicked.call()
    }

    fun setCurrentStoryFilter(filter: String) {
        _currentStoryFilter.value = filter
    }
}