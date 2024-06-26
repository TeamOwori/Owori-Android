package com.owori.android.presenter.main.story.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.model.CommentData
import com.owori.android.presenter.model.PostData
import com.owori.android.presenter.model.PostPhotoData
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : BaseViewModel() {
    private val _finishButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val finishButtonClicked: LiveData<Unit> = _finishButtonClicked

    private val _clearButtonClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val clearButtonClicked: LiveData<Unit> = _clearButtonClicked

    val searchKeyWord: MutableLiveData<String> = MutableLiveData()

    private val _recentSearchKeywords: MutableLiveData<List<String>> = MutableLiveData(listOf())
    val recentSearchKeywords: LiveData<List<String>> = _recentSearchKeywords

    private val _searchResult: MutableLiveData<List<PostData>> = MutableLiveData(listOf())
    val searchResult: LiveData<List<PostData>> = _searchResult

    init {
        fetchRecentResultSearchKeyword()
        fetchSearchResult()
    }

    fun onClickFinishButton() {
        _finishButtonClicked.call()
    }

    fun onClickClearButton() {
        _clearButtonClicked.call()
    }

    fun clearSearchKeyWord() {
        searchKeyWord.value = ""
    }

    fun removeAllRecentSearchKeyword() {
        _recentSearchKeywords.value = listOf()
        // DELETE API
    }

    fun removeRecentSearchKeyword(searchKeyword: String) {
        _recentSearchKeywords.value = _recentSearchKeywords.value?.filter { keyword ->
            keyword != searchKeyword
        }
        // DELETE API
    }

    fun setSearchKeyword(keyword: String) {
        searchKeyWord.value = keyword
    }

    private fun fetchRecentResultSearchKeyword() {
        _recentSearchKeywords.value = listOf(
            "동해바다",
            "속초",
            "여행",
            "택배"
        )
    }

    private fun fetchSearchResult() {
        _searchResult.value = listOf(
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
    }
}