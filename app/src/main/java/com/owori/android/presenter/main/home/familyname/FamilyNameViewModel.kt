package com.owori.android.presenter.main.home.familyname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FamilyNameViewModel @Inject constructor() : BaseViewModel() {
    private val _saveButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val saveButtonClicked: LiveData<Unit> = _saveButtonClicked

    private val _familyGroupName: MutableLiveData<String> = MutableLiveData()
    val familyGroupName: LiveData<String> = _familyGroupName

    init {
        initFamilyGroupName()
    }

    fun onClickSaveButton() {
        _saveButtonClicked.call()
    }

    fun saveFamilyGroupName(name: String) {
        // TODO : API 연동
    }

    private fun initFamilyGroupName() {
        _familyGroupName.value = "지렁이"
    }
}