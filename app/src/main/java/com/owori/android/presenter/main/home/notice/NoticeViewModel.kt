package com.owori.android.presenter.main.home.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.model.NoticeData
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor() : BaseViewModel() {
    private val _closeButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val closeButtonClicked: LiveData<Unit> = _closeButtonClicked

    private val _noticeList: MutableLiveData<List<NoticeData>> = MutableLiveData()
    val noticeList: LiveData<List<NoticeData>> = _noticeList

    init {
        fetchNoticeList()
    }

    fun fetchNoticeList() {
        // TODO : API 연동 후, 데이터 fetch 로직 추가
        _noticeList.value = listOf(
            NoticeData(0, null, "지렁이님이 메시지를 보냈습니다.", "나 대신 택배 받아줘", "05/21 12:34"),
                    NoticeData(0, null, "슾툰훈님이 메시지를 보냈습니다.", "Honey~ oh Honey~", "05/21 12:34"),
            NoticeData(0, null, "말왕님이 메시지를 보냈습니다.", "장충동왕족발보쌈 🤩", "05/21 12:34")
        )
    }

    fun onClickCloseButton() {
        _closeButtonClicked.call()
    }
}