package com.owori.android.common.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.owori.android.common.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

open class BaseViewModel : ViewModel() {
    protected val _navigation = SingleLiveEvent<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    companion object {
        private const val TAG = "BaseViewModel"
    }
}