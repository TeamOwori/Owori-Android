package com.owori.android.presenter.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.R
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.model.DdayData
import com.owori.android.presenter.model.FamilyInfo
import com.owori.android.presenter.model.MemberItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    private val _familyEmotionList: MutableLiveData<List<MemberItem>> = MutableLiveData()
    val familyEmotionList: LiveData<List<MemberItem>> = _familyEmotionList
    private val _dDayList: MutableLiveData<List<DdayData>> = MutableLiveData()
    val dDayList: LiveData<List<DdayData>> = _dDayList
    private val _familyInfo: MutableLiveData<FamilyInfo> = MutableLiveData()
    val familyInfo: LiveData<FamilyInfo> = _familyInfo

    init {
        fetchFamilyEmotionList()
        fetchDdayList()
        fetchFamilyInfo()
    }

    fun fetchFamilyEmotionList() {
        _familyEmotionList.value = listOf(
            MemberItem(0, "나", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Elon_Musk_Royal_Society_%28crop2%29.jpg/225px-Elon_Musk_Royal_Society_%28crop2%29.jpg", R.drawable.emoji_excited),
            MemberItem(1, "아빠", "https://images.unsplash.com/photo-1609440082470-106df86c0f6c?auto=format&fit=crop&q=80&w=3377&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", R.drawable.emoji_cool),
            MemberItem(2, "엄마", "https://images.unsplash.com/photo-1542385151-efd9000785a0?auto=format&fit=crop&q=80&w=3389&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", R.drawable.emoji_happy),
            MemberItem(3, "형", "https://cdn.ceomagazine.co.kr/news/photo/202108/30233_20491_1559.jpg", null),
        )
    }

    fun fetchDdayList() {
        _dDayList.value = listOf(
            DdayData(0, "오워리 오프라인 모임", "D-day", "10월 25일 (수)"),
            DdayData(1, "가족 저녁식사", "D-1", "10월 26일 (목)"),
            DdayData(2, "가족 점심식사", "D-2", "10월 27일 (금)"),
            DdayData(3, "배추김치 김장날", "D-3", "10월 28일 (토)"),
            DdayData(4, "할머니댁 방문", "D-4", "10월 29일 (일)"),
        )
        Log.d("fetchDday", "${_dDayList.value}")
    }

    fun deleteDdayItem(id: Int) {
        _dDayList.value = _dDayList.value?.filter { it.id != id }
    }

    fun fetchFamilyInfo() {
        _familyInfo.value = FamilyInfo(0, "우당탕탕 우리 가족 ❤️")
    }
}