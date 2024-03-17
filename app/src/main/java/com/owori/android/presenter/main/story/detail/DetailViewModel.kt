package com.owori.android.presenter.main.story.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.model.CommentData
import com.owori.android.presenter.model.PhotoData
import com.owori.android.presenter.model.PostData
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : BaseViewModel() {
    private val _photoList: MutableLiveData<List<PhotoData>> = MutableLiveData(listOf())
    val photoList: LiveData<List<PhotoData>> = _photoList
    private val _finishButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val finishButtonClicked: LiveData<Unit> = _finishButtonClicked
    private val _shareButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val shareButtonClicked: LiveData<Unit> = _shareButtonClicked
    private val _moreButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val moreButtonClicked: LiveData<Unit> = _moreButtonClicked
    private val _postData: MutableLiveData<PostData> = MutableLiveData()
    val postData: LiveData<PostData> = _postData

    init {
        initPostData()
        initPhotoList()
    }

    private fun initPostData() {
        _postData.value = PostData(
            0,
            null,
            "안녕하세요!!",
            "오월이 컨텐츠컨텐츠 입니다~~~",
            "2024-03-01",
            "2024-03-19",
            true,
            1,
            listOf<CommentData>(),
            "쥐렁이"
        )
    }

    private fun initPhotoList() {

    }

    fun onClickFinishButton() {
        _finishButtonClicked.call()
    }

    fun onClickMoreButton() {
        _moreButtonClicked.call()
    }

    fun onClickShareButton() {
        _shareButtonClicked.call()
    }

    fun onClickLikedButton() {
        _postData.value?.let { postDataValue ->
            Log.d("liked", "${postDataValue.liked}")
            _postData.value = postDataValue.copy(liked = !postDataValue.liked)
        }
    }
}