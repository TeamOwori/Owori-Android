package com.owori.android.presenter.main.story.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor() : BaseViewModel() {
    private val _showCancelPostDialog: SingleLiveEvent<Unit> = SingleLiveEvent()
    val showCancelPostDialog: LiveData<Unit> = _showCancelPostDialog

    private val _savePostButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val savePostButtonClicked: LiveData<Unit> = _savePostButtonClicked

    private val _isPostModeActivated: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPostModeActivated: LiveData<Boolean> = _isPostModeActivated

    private val _showImagePicker: SingleLiveEvent<Unit> = SingleLiveEvent()
    val showImagePicker: LiveData<Unit> = _showImagePicker

    private val _startDate: MutableLiveData<String> = MutableLiveData()
    val startDate: LiveData<String> = _startDate

    private val _endDate: MutableLiveData<String> = MutableLiveData()
    val endDate: LiveData<String> = _endDate

    fun onClickChooseImageButton() {
        _showImagePicker.call()
    }

    fun onClickCancelPostButton() {
        _showCancelPostDialog.call()
    }

    fun onClickSavePostButton() {
        if (_isPostModeActivated.value == true) {
            _savePostButtonClicked.call()
        }
    }

    fun setPostMode(isActivated: Boolean) {
        _isPostModeActivated.value = isActivated
    }
}