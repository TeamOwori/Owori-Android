package com.owori.android.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.common.SingleLiveEvent
import com.owori.android.common.ui.viewmodel.BaseViewModel
import javax.inject.Inject

class BirthDateViewModel @Inject constructor() : BaseViewModel() {
    private val _btnNext: SingleLiveEvent<Unit> = SingleLiveEvent()
    val btnNext: LiveData<Unit> = _btnNext
    private val _btnBack: SingleLiveEvent<Unit> = SingleLiveEvent()
    val btnBack: LiveData<Unit> = _btnBack

    val _birthDate: MutableLiveData<String> = MutableLiveData("")
    val birthDate: LiveData<String> = _birthDate

    fun onClickCheckButton() {
        _btnNext.call()
    }

    fun onClickBackButton() {
        _btnBack.call()
    }
}