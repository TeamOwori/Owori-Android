package com.owori.android.presenter.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.model.MyColor.PURPLE
import com.owori.android.presenter.model.MyPageData
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor() : BaseViewModel() {
    private val _closeButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val closeButtonClicked: LiveData<Unit> = _closeButtonClicked
    private val _editButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val editButtonClicked: LiveData<Unit> = _editButtonClicked
    private val _settingsButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val settingsButtonClicked: LiveData<Unit> = _settingsButtonClicked
    private val _saveButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val saveButtonClicked: LiveData<Unit> = _saveButtonClicked
    private val _isEditMode: MutableLiveData<Boolean> = MutableLiveData(false)
    val isEditMode: LiveData<Boolean> = _isEditMode
    private val _myProfile: MutableLiveData<MyPageData> = MutableLiveData()
    val myProfile: LiveData<MyPageData> = _myProfile

    init {
        fetchMyData()
    }

    fun onClickCloseButton() {
        _closeButtonClicked.call()
    }

    fun onClickEditButton() {
        _isEditMode.value = true
    }

    fun onClickSettingsButton() {
        _settingsButtonClicked.call()
    }

    fun onClickSaveButton() {
        _isEditMode.value = false
        _saveButtonClicked.call()
    }

    fun fetchMyData() {
        _myProfile.value = MyPageData("지렁이", "2020-11-30", PURPLE, "")
    }

    fun saveMyData() {
        // TODO : 내 정보 저장 기능 구현
    }
}