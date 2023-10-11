package com.owori.android.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.owori.android.presenter.util.SingleLiveEvent

open class BaseViewModel : ViewModel() {
    protected val _navigation = SingleLiveEvent<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    companion object {
        private const val TAG = "BaseViewModel"
    }
}