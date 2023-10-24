package com.owori.android.presenter.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.R
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.model.DdayData
import com.owori.android.presenter.model.FamilyInfo
import com.owori.android.presenter.model.FamilyPhotoData
import com.owori.android.presenter.model.EmotionItem
import com.owori.android.presenter.model.FamilyMemberData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    private val _familyEmotionList: MutableLiveData<List<EmotionItem>> = MutableLiveData()
    val familyEmotionList: LiveData<List<EmotionItem>> = _familyEmotionList
    private val _dDayList: MutableLiveData<List<DdayData>> = MutableLiveData()
    val dDayList: LiveData<List<DdayData>> = _dDayList
    private val _familyPhotoList: MutableLiveData<List<FamilyPhotoData>> = MutableLiveData()
    val familyPhotoList: LiveData<List<FamilyPhotoData>> = _familyPhotoList
    private val _familyInfo: MutableLiveData<FamilyInfo> = MutableLiveData()
    val familyInfo: LiveData<FamilyInfo> = _familyInfo
    private val _familyMemberList: MutableLiveData<List<FamilyMemberData>> = MutableLiveData()
    val familyMemberList: LiveData<List<FamilyMemberData>> = _familyMemberList

    init {
        fetchFamilyEmotionList()
        fetchDdayList()
        fetchFamilyInfo()
        fetchFamilyPhotoList()
        fetchFamilyMemberData()
    }

    fun fetchFamilyEmotionList() {
        _familyEmotionList.value = listOf(
            EmotionItem(0, "나", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Elon_Musk_Royal_Society_%28crop2%29.jpg/225px-Elon_Musk_Royal_Society_%28crop2%29.jpg", R.drawable.emoji_excited),
            EmotionItem(1, "아빠", "https://images.unsplash.com/photo-1609440082470-106df86c0f6c?auto=format&fit=crop&q=80&w=3377&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", R.drawable.emoji_cool),
            EmotionItem(2, "엄마", "https://images.unsplash.com/photo-1542385151-efd9000785a0?auto=format&fit=crop&q=80&w=3389&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", R.drawable.emoji_happy),
            EmotionItem(3, "형", "https://cdn.ceomagazine.co.kr/news/photo/202108/30233_20491_1559.jpg", null),
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
    }

    fun fetchFamilyPhotoList() {
        _familyPhotoList.value = listOf(
            FamilyPhotoData(0, "https://cdn.nanamcom.co.kr/news/photo/202306/2292_7667_36.jpg"),
            FamilyPhotoData(0, "https://www.sisajournal.com/news/photo/first/201706/img_169929_1.png"),
            FamilyPhotoData(0, "https://img3.yna.co.kr/etc/inner/KR/2020/08/18/AKR20200818040200009_01_i_P2.jpg"),
            FamilyPhotoData(0, "https://img2.sbs.co.kr/ops_clip_img/2020/05/14/32709ee9-0d73-4ac4-82ce-9266e6ff7700216w640.jpg"),
            FamilyPhotoData(0, "https://i.ytimg.com/vi/ciWImATlh2Q/maxresdefault.jpg"),
        )
    }

    fun deleteDdayItem(id: Int) {
        _dDayList.value = _dDayList.value?.filter { it.id != id }
    }

    fun fetchFamilyInfo() {
        _familyInfo.value = FamilyInfo(0, "우당탕탕 우리 가족 ❤️", FamilyMemberData(0, "나", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Elon_Musk_Royal_Society_%28crop2%29.jpg/225px-Elon_Musk_Royal_Society_%28crop2%29.jpg", "화성 갈끄니까아악~ 🚀"))
    }

    fun fetchFamilyMemberData() {
        _familyMemberList.value = listOf(
            FamilyMemberData(0, "아빠", "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2023/09/16/1ba2d3b6-770b-4370-af87-57d08ed46f63.jpg", "아빠 안 잔다...."),
            FamilyMemberData(1, "엄마", "https://ojsfile.ohmynews.com/STD_IMG_FILE/2022/1202/IE003085931_STD.jpg", "어머 얘 서준아~ 정말~"),
            FamilyMemberData(2, "형", "https://news.einfomax.co.kr/news/photo/202302/4253138_140028_92.jpg", "그 쪽도 홍박사님을 아세요~?"),
        )
    }
}