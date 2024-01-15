package com.owori.android.presenter.main.story.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.model.Filter.DATE
import com.owori.android.presenter.model.Filter.RECENT
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class FilterViewModel @AssistedInject constructor(@Assisted private val filter: String) :
    BaseViewModel() {
    private val _recentSortClicked: MutableLiveData<Boolean> =
        MutableLiveData(filter == RECENT.label)
    val recentSortClicked: LiveData<Boolean> = _recentSortClicked

    private val _dateSortClicked: MutableLiveData<Boolean> = MutableLiveData(filter == DATE.label)
    val dateSortClicked: LiveData<Boolean> = _dateSortClicked

    private val _selectedFilter: MutableLiveData<String> = MutableLiveData(filter)
    val selectedFilter: LiveData<String> = _selectedFilter

    private val _isFirst: MutableLiveData<Boolean> = MutableLiveData(true)
    val isFirst: LiveData<Boolean> = _isFirst

    fun onClickRecentSort() {
        _recentSortClicked.value = true
        _dateSortClicked.value = false
        _selectedFilter.value = RECENT.label
        _isFirst.value = false
    }

    fun onClickDateSort() {
        _dateSortClicked.value = true
        _recentSortClicked.value = false
        _selectedFilter.value = DATE.label
        _isFirst.value = false
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(filter: String): FilterViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            filter: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(filter) as T
            }
        }
    }
}