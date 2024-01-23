package com.owori.android.presenter.main.home.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.model.ColorStatus
import com.owori.android.presenter.model.ColorStatus.ABLE
import com.owori.android.presenter.model.ColorStatus.CHECKED
import com.owori.android.presenter.model.ColorStatus.DISABLED
import com.owori.android.presenter.model.MyColorType
import com.owori.android.presenter.model.MyColorType.AZURE
import com.owori.android.presenter.model.MyColorType.GREEN
import com.owori.android.presenter.model.MyColorType.NAVY
import com.owori.android.presenter.model.MyColorType.PINK
import com.owori.android.presenter.model.MyColorType.PURPLE
import com.owori.android.presenter.model.MyColorType.RED
import com.owori.android.presenter.model.MyColorType.YELLOW
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

    private val _myColorStatus: MutableLiveData<MutableMap<MyColorType, ColorStatus>> =
        MutableLiveData()
    val myColorStatus: LiveData<MutableMap<MyColorType, ColorStatus>> = _myColorStatus

    private val _showTedImagePicker: SingleLiveEvent<Unit> = SingleLiveEvent()
    val showTedImagePicker: LiveData<Unit> = _showTedImagePicker

    init {
        fetchMyData()
        fetchMyColor()
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
        _saveButtonClicked.call()
    }

    fun fetchMyData() {
        // TODO : API 연동 후, 데이터 fetch 로직 추가
        _myProfile.value = MyPageData("지렁이", "2020-11-30", PURPLE, null, 1, 3, 5)
    }

    fun fetchMyColor() {
        // TODO : API 연동 후, 데이터 fetch 로직 추가
        _myColorStatus.value = hashMapOf(
            RED to ABLE,
            PINK to ABLE,
            YELLOW to ABLE,
            GREEN to DISABLED,
            AZURE to DISABLED,
            NAVY to ABLE,
            PURPLE to CHECKED
        )
    }

    fun saveMyData(name: String, birth: String) {
        // TODO : 내 정보 저장 기능 구현
        _myProfile.value = _myColorStatus.value?.let { myColor ->
            MyPageData(
                name,
                birth,
                myColor.filter { it.value == CHECKED }.keys.first(),
                _myProfile.value?.profileImage,
                1, 3, 5
            )
        }
        _isEditMode.value = false
    }

    fun onClickMyColor(myColorType: MyColorType) {
        _myColorStatus.value?.let { myColorStatusValue ->
            if (myColorStatusValue[myColorType] != DISABLED) {
                myColorStatusValue[myColorStatusValue.filterValues { value -> value == CHECKED }.keys.first()] =
                    ABLE
                myColorStatusValue[myColorType] = CHECKED
            }
        }
        _myColorStatus.value = _myColorStatus.value?.toMutableMap()
    }

    fun onClickEditProfileImage() {
        if (_isEditMode.value == true) {
            _showTedImagePicker.call()
        }
    }

    fun setProfileImage(uriString: String) {
        _myProfile.value = _myProfile.value?.copy(profileImage = uriString)
    }
}