package com.owori.android.presenter.policy

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.owori.android.core.BaseViewModel
import com.owori.android.presenter.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShareCodeViewModel @Inject constructor() : BaseViewModel() {
    init {
        val timer: CountDownTimer = object : CountDownTimer(MIllIS_IN_FUTURE, TICK_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                val minute = millisUntilFinished / 60000L
                val second = millisUntilFinished % 60000L / 1000L
                _time.value = "${minute}:${second}"
                timerJob.start()
            }

            override fun onFinish() {

            }
        }.start()
    }
    private val _btnNext: SingleLiveEvent<Unit> = SingleLiveEvent()
    val btnNext: LiveData<Unit> = _btnNext

    private val _btnShare: SingleLiveEvent<Unit> = SingleLiveEvent()
    val btnShare: LiveData<Unit> = _btnShare

    private val _btnBack: SingleLiveEvent<Unit> = SingleLiveEvent()
    val btnBack: LiveData<Unit> = _btnBack
    private val _customTimerDuration: MutableLiveData<Long> = MutableLiveData(MIllIS_IN_FUTURE)
    private var oldTimeMills: Long = 0
    private val _time = MutableLiveData<String>("")
    val timerJob: Job = viewModelScope.launch(start = CoroutineStart.LAZY) {
        withContext(Dispatchers.IO) {
            oldTimeMills = System.currentTimeMillis()
            while (_customTimerDuration.value!! > 0L) {
                val delayMills = System.currentTimeMillis() - oldTimeMills
                if (delayMills == TICK_INTERVAL) {
                    _customTimerDuration.postValue(_customTimerDuration.value!! - delayMills)
                    oldTimeMills = System.currentTimeMillis()
                }
            }
        }
    }
    val time: LiveData<String> = _time



    fun onClickCheckButton() {
        _btnNext.call()
    }

    fun onClickShareButton() {
        _btnShare.call()
    }

    fun onClickBackButton() {
        _btnBack.call()
    }

    companion object {
        const val MIllIS_IN_FUTURE = 1800000L
        const val TICK_INTERVAL = 1000L
    }
}