package com.owori.android.presenter.policy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputFamilyCodeViewModel @Inject constructor(): BaseViewModel() {
    val _inputCode: MutableLiveData<String> = MutableLiveData("")
    val inputCode: LiveData<String> = _inputCode
}