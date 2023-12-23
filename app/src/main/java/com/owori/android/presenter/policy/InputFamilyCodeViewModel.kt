package com.owori.android.presenter.policy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.owori.android.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputFamilyCodeViewModel @Inject constructor(): BaseViewModel() {
    private val _inputCodeResult: MutableLiveData<Boolean?> = MutableLiveData<Boolean?>(null)
    val inputCodeResult: LiveData<Boolean?> = _inputCodeResult
    fun codeExist(code: String) = viewModelScope.launch {
            _inputCodeResult.value = true
    }
}