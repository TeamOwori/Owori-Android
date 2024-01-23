package com.owori.android.presenter.main.story.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.core.DateFormatter
import com.owori.android.presenter.model.PhotoData
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
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

    private val _startDate: MutableLiveData<String> = MutableLiveData(DateFormatter.toDashDateFormat(
        Calendar.getInstance().timeInMillis))
    val startDate: LiveData<String> = _startDate

    private val _endDate: MutableLiveData<String> = MutableLiveData(_startDate.value)
    val endDate: LiveData<String> = _endDate

    private val _showDatePickerDialog: SingleLiveEvent<Unit> = SingleLiveEvent()
    val showDatePickerDialog: LiveData<Unit> = _showDatePickerDialog

    private val _photoList: MutableLiveData<List<PhotoData>> = MutableLiveData(listOf())
    val photoList: LiveData<List<PhotoData>> = _photoList

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

    fun onClickDatePickerDialogButton() {
        _showDatePickerDialog.call()
    }

    fun setPhotoList(photoList: List<PhotoData>) {
        _photoList.value = photoList
    }

    fun onClickDeletePhotoButton(id: Int) {
        _photoList.value = _photoList.value?.filter { photo ->
            photo.id != id
        }?.mapIndexed{ index, photo ->
            if (index == 0) {
                PhotoData(true, photo.id, photo.imageSrc)
            } else {
                photo
            }
        }
    }

    fun setDate(startDate: String, endDate: String) {
        _startDate.value = startDate
        _endDate.value = endDate
    }
}