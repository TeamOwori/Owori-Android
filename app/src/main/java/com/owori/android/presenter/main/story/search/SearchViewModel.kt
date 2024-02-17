package com.owori.android.presenter.main.story.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.model.PostData
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

    private val _searchResult: MutableLiveData<List<PostData>> = MutableLiveData(listOf())
    val searchResult: LiveData<List<PostData>> = _searchResult

    init {
        fetchRecentResultSearchKeyword()
    }

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
        // DELETE API
    }

    fun removeRecentSearchKeyword(searchKeyword: String) {
        _recentSearchKeywords.value = _recentSearchKeywords.value?.filter { keyword ->
            keyword != searchKeyword
        }
        // DELETE API
    }

    fun setSearchKeyword(keyword: String) {
        searchKeyWord.value = keyword
    }

    private fun fetchRecentResultSearchKeyword() {
        _recentSearchKeywords.value = listOf(
            "동해바다",
            "속초",
            "여행",
            "택배"
        )
    }
}