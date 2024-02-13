package com.owori.android.presenter.main.story.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : BaseViewModel() {
    private val _finishButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val finishButtonClicked: LiveData<Unit> = _finishButtonClicked

    private val _clearButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val clearButtonClicked: LiveData<Unit> = _clearButtonClicked

    val searchKeyWord: MutableLiveData<String> = MutableLiveData()

    private val _recentSearchKeywords: MutableLiveData<List<String>> = MutableLiveData(listOf())
    val recentSearchKeywords: LiveData<List<String>> = _recentSearchKeywords

    private val _searchResult: MutableLiveData<List<Any>> = MutableLiveData()
    val searchResult: LiveData<List<Any>> = _searchResult

    fun onClickFinishButton() {
        _finishButtonClicked.call()
    }

    fun onClickClearButton() {
        _clearButtonClicked.call()
    }

    fun clearSearchKeyWord() {
        searchKeyWord.value = ""
    }

    fun removeAllRecentSearchKeyword() {
        _recentSearchKeywords.value = listOf()
    }
}