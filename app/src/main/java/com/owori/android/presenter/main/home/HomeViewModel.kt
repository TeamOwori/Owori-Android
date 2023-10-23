package com.owori.android.presenter.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.R
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.model.MemberItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    private val _familyEmotionList: MutableLiveData<List<MemberItem>> = MutableLiveData()
    val familyEmotionList: LiveData<List<MemberItem>> = _familyEmotionList

    init {
        fetchFamilyEmotionList()
    }

    fun fetchFamilyEmotionList() {
        _familyEmotionList.value = listOf(
            MemberItem(0, "나", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Elon_Musk_Royal_Society_%28crop2%29.jpg/225px-Elon_Musk_Royal_Society_%28crop2%29.jpg", R.drawable.emoji_excited),
            MemberItem(1, "아빠", "https://images.unsplash.com/photo-1609440082470-106df86c0f6c?auto=format&fit=crop&q=80&w=3377&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", R.drawable.emoji_cool),
            MemberItem(2, "엄마", "https://images.unsplash.com/photo-1542385151-efd9000785a0?auto=format&fit=crop&q=80&w=3389&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", R.drawable.emoji_happy),
            MemberItem(3, "형", "https://cdn.ceomagazine.co.kr/news/photo/202108/30233_20491_1559.jpg", null),
        )
    }
}