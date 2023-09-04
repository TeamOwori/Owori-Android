package com.owori.android.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.common.SingleLiveEvent
import com.owori.android.common.ui.viewmodel.BaseViewModel

class OnBoardingViewModel : BaseViewModel() {
    private val _currentItemIndex: MutableLiveData<Int> = MutableLiveData(0)
    val currentItemIndex: LiveData<Int> = _currentItemIndex
    private val _finishOnBoarding: SingleLiveEvent<Unit> = SingleLiveEvent();
    val finishOnBoarding: LiveData<Unit> = _finishOnBoarding;

    fun setCurrentItemIndex(index: Int) {
        _currentItemIndex.value = index
    }

    fun onClickNextButton() {
        _currentItemIndex.value?.let { _index ->
            if (_index == LAST_PAGE) _finishOnBoarding.call()
            else _currentItemIndex.value = _currentItemIndex.value?.plus(1)
        }
    }

    companion object {
        private const val LAST_PAGE = 2
    }
}
