package com.owori.android.presenter.main.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.ext.extractMonthFromDate
import com.owori.android.presenter.ext.extractYearFromDate
import com.owori.android.presenter.model.CommentData
import com.owori.android.presenter.model.Filter.RECENT
import com.owori.android.presenter.model.PostData
import com.owori.android.presenter.model.PostPhotoData
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor() : BaseViewModel() {
    private val _searchButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val searchButtonClicked: LiveData<Unit> = _searchButtonClicked

    private val _postStoryButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val postStoryButtonClicked: LiveData<Unit> = _postStoryButtonClicked

    private val _filterLayoutClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val filterLayoutClicked: LiveData<Unit> = _filterLayoutClicked

    private val _currentStoryFilter: MutableLiveData<String> = MutableLiveData(RECENT.label)
    val currentStoryFilter: LiveData<String> = _currentStoryFilter

    private val _isListMode: MutableLiveData<Boolean> = MutableLiveData(true)
    val isListMode: LiveData<Boolean> = _isListMode

    private val _postList: MutableLiveData<List<PostData>> = MutableLiveData()
    val postList: LiveData<List<PostData>> = _postList

    private val _galleryList: MutableLiveData<MutableList<Any>> = MutableLiveData()
    val galleryList: LiveData<MutableList<Any>> = _galleryList

    init {
        initPostList()
        initPostListWithHeader()
    }

    fun onClickSearchButton() {
        _searchButtonClicked.call()
    }

    fun onClickListViewMode() {
        if (_isListMode.value != true) {
            _isListMode.value = true
        }
    }

    fun onClickCardViewMode() {
        if (_isListMode.value == true) {
            _isListMode.value = false
        }
    }

    fun onClickPostStoryButton() {
        _postStoryButtonClicked.call()
    }

    fun onClickFilterLayout() {
        _filterLayoutClicked.call()
    }

    fun setCurrentStoryFilter(filter: String) {
        _currentStoryFilter.value = filter
    }

    fun initPostList() {
        // TODO : API 연동 필요
        _postList.value = listOf(
            PostData(
                id = 0,
                photoList = listOf(
                    PostPhotoData(
                        0,
                        "https://mblogthumb-phinf.pstatic.net/MjAyMTA0MTRfMzEg/MDAxNjE4MzkzNzYyMTI3.2O_46TmKTYGbXMeu-W_9wdfAAw-tYLPzzRO2ZhB7hesg.iBmAqi17fck3tC4907gGaFIp1IalksYgIGCdjhhSuzAg.JPEG.eu2/1618393754909.jpg?type=w800"
                    ),
                    PostPhotoData(
                        1, "https://cdn.imweb.me/upload/S20210809c06cc49e8b65a/21eaaf7839ec5.jpg"
                    ),
                ),
                title = "무슨 영화세트장인줄..!",
                contents = "엄마가 추천해줘서 간 연암지.. 풍경이 미쳤다.! 특히 해질 때 노을이랑 같이 보니까 극락온줄알았어요..",
                startDate = "2023.03.12",
                endDate = "2023.03.13",
                likeCount = 4,
                commentList = listOf(CommentData(0, "꺄르륵", "ㅎㅇㅎㅇㅎㅇ")),
                author = "쥐렁이"
            ),
            PostData(
                id = 0, photoList = listOf(
                    PostPhotoData(
                        1,
                        "https://cdn.imweb.me/upload/S20210809c06cc49e8b65a/21eaaf7839ec5.jpg"
                    ),
                ), title = "무슨 영화세트장인줄..!",
                contents = "엄마가 추천해줘서 간 연암지.. 풍경이 미쳤다.! 특히 해질 때 노을이랑 같이 보니까 극락온줄알았어요..",
                startDate = "2023.03.13",
                likeCount = 4,
                commentList = listOf(CommentData(0, "꺄르륵", "ㅎㅇㅎㅇㅎㅇ")),
                author = "쥐렁이"
            ),
            PostData(
                id = 0, photoList = listOf(
                    PostPhotoData(
                        1,
                        "https://cdn.imweb.me/upload/S20210809c06cc49e8b65a/21eaaf7839ec5.jpg"
                    ),
                ), title = "무슨 영화세트장인줄..!",
                contents = "엄마가 추천해줘서 간 연암지.. 풍경이 미쳤다.! 특히 해질 때 노을이랑 같이 보니까 극락온줄알았어요..",
                startDate = "2023.04.12",
                likeCount = 4,
                commentList = listOf(CommentData(0, "꺄르륵", "ㅎㅇㅎㅇㅎㅇ")),
                author = "쥐렁이"
            ),
            PostData(
                id = 0, photoList = listOf(
                    PostPhotoData(
                        1,
                        "https://cdn.imweb.me/upload/S20210809c06cc49e8b65a/21eaaf7839ec5.jpg"
                    ),
                ), title = "무슨 영화세트장인줄..!",
                contents = "엄마가 추천해줘서 간 연암지.. 풍경이 미쳤다.! 특히 해질 때 노을이랑 같이 보니까 극락온줄알았어요..",
                startDate = "2023.04.12",
                endDate = "2023.04.13",
                likeCount = 4,
                commentList = listOf(CommentData(0, "꺄르륵", "ㅎㅇㅎㅇㅎㅇ")),
                author = "쥐렁이"
            ),
            PostData(
                id = 0, photoList = listOf(
                    PostPhotoData(
                        1,
                        "https://cdn.imweb.me/upload/S20210809c06cc49e8b65a/21eaaf7839ec5.jpg"
                    ),
                ), title = "무슨 영화세트장인줄..!",
                contents = "엄마가 추천해줘서 간 연암지.. 풍경이 미쳤다.! 특히 해질 때 노을이랑 같이 보니까 극락온줄알았어요..",
                startDate = "2023.04.12",
                endDate = "2023.04.13",
                likeCount = 4,
                commentList = listOf(CommentData(0, "꺄르륵", "ㅎㅇㅎㅇㅎㅇ")),
                author = "쥐렁이"
            ),
            PostData(
                id = 0, photoList = listOf(
                    PostPhotoData(
                        1,
                        "https://cdn.imweb.me/upload/S20210809c06cc49e8b65a/21eaaf7839ec5.jpg"
                    ),
                ), title = "무슨 영화세트장인줄..!",
                contents = "엄마가 추천해줘서 간 연암지.. 풍경이 미쳤다.! 특히 해질 때 노을이랑 같이 보니까 극락온줄알았어요..",
                startDate = "2023.04.12",
                endDate = "2023.04.13",
                likeCount = 4,
                commentList = listOf(CommentData(0, "꺄르륵", "ㅎㅇㅎㅇㅎㅇ")),
                author = "쥐렁이"
            ),
            PostData(
                id = 0, photoList = listOf(
                    PostPhotoData(
                        1,
                        "https://cdn.imweb.me/upload/S20210809c06cc49e8b65a/21eaaf7839ec5.jpg"
                    ),
                ), title = "무슨 영화세트장인줄..!",
                contents = "엄마가 추천해줘서 간 연암지.. 풍경이 미쳤다.! 특히 해질 때 노을이랑 같이 보니까 극락온줄알았어요..",
                startDate = "2023.04.12",
                endDate = "2023.04.13",
                likeCount = 4,
                commentList = listOf(CommentData(0, "꺄르륵", "ㅎㅇㅎㅇㅎㅇ")),
                author = "쥐렁이"
            )
        )
        initPostListWithHeader()
    }

    fun initPostListWithHeader() {
        _galleryList.value = _postList.value?.toMutableList()
        _galleryList.value?.let { galleryListValue ->
            val date = getDate(galleryListValue.first())
            var year = date.first
            var month = date.second

            for (i in galleryListValue.indices) {
                if (galleryListValue[i] is PostData) {
                    val date = (galleryListValue[i] as PostData).startDate
                    val curYear = date.extractYearFromDate()
                    val curMonth = date.extractMonthFromDate()

                    if (year != curYear || month != curMonth) {
                        year = curYear
                        month = curMonth
                        galleryListValue.add(i, "$curYear.$curMonth")
                    }
                }
            }
        }
    }

    private fun checkGalleryHeader(dateString: String): Pair<Int, Int> {
        val yearMonth = dateString.split(".")
        return Pair(yearMonth.first().toInt(), yearMonth.last().toInt())
    }

    private fun getDate(value: Any): Pair<Int, Int> {
        return if (value is PostData) {
            val year = value.startDate.extractYearFromDate()
            val month = value.startDate.extractMonthFromDate()
            _galleryList.value?.add(0, "$year.$month")
            Pair(year, month)
        } else {
            checkGalleryHeader(value as String)
        }
    }
}