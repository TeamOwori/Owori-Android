package com.owrori.android.ui.common.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.owrori.android.ui.common.SingleLiveEvent
import org.koin.core.component.KoinComponent

open class BaseViewModel : ViewModel(), KoinComponent {
    protected val _navigation = SingleLiveEvent<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    companion object {
        private const val TAG = "BaseViewModel"
    }
}