package com.owori.android.presenter.policy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(): BaseViewModel() {
    val _groupname: MutableLiveData<String> = MutableLiveData("")
    val groupname: LiveData<String> = _groupname

    private val _btnNext: SingleLiveEvent<Unit> = SingleLiveEvent()
    val btnNext: LiveData<Unit> = _btnNext
    private val _btnBack: SingleLiveEvent<Unit> = SingleLiveEvent()
    val btnBack: LiveData<Unit> = _btnBack

    fun onClickCheckButton() {
        _btnNext.call()
    }

    fun onClickBackButton() {
        _btnBack.call()
    }
}