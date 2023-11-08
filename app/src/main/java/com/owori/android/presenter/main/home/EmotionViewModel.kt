package com.owori.android.presenter.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.model.EmotionItem
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmotionViewModel @Inject constructor() : BaseViewModel() {
    private val _closeButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val closeButtonClicked: LiveData<Unit> = _closeButtonClicked
    private val _emotionList: MutableLiveData<List<EmotionItem>> = MutableLiveData()
    val emotionList: LiveData<List<EmotionItem>> = _emotionList
    private val _currentEmotion: MutableLiveData<EmotionItem> = MutableLiveData()
    val currentEmotion: LiveData<EmotionItem> = _currentEmotion
    private val _callClickSubmitButton: SingleLiveEvent<Unit> = SingleLiveEvent()
    val callClickSubmitButton: LiveData<Unit> = _callClickSubmitButton

    fun onClickCloseButton() {
        _closeButtonClicked.call()
    }

    fun onClickEmotionItem(selectedEmotionItem: EmotionItem) {
        _emotionList.value = _emotionList.value?.map { _emotionItem ->
            if (_emotionItem.id == selectedEmotionItem.id){
                _currentEmotion.value = _emotionItem.copy(isChecked = !_emotionItem.isChecked)
                _emotionItem.copy(isChecked = true)
            }
            else _emotionItem.copy(isChecked = false)
        }
    }

    fun onClickSubmitButton() {
        _currentEmotion.value?.let { setEmotion(it) }
        _callClickSubmitButton.call()
    }

    fun setEmotionList(emotions: List<EmotionItem>) {
        _emotionList.value = emotions
    }

    private fun setEmotion(selectedEmotionItem: EmotionItem) {
        // TODO : 서버로 감정 PUT
    }
}