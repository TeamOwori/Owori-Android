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
                val minute = millisUntilFinished / MINUTE_UNIT
                val second = millisUntilFinished % MINUTE_UNIT / SECOND_UNIT
                _time.value = "${minute}:${second}"
                timerJob.start()
            }

            override fun onFinish() = Unit
        }.start()
    }

    private val _customTimerDuration: MutableLiveData<Long> = MutableLiveData(MIllIS_IN_FUTURE)
    private var oldTimeMills: Long = 0
    private val _time = MutableLiveData<String>("")
    val timerJob: Job = viewModelScope.launch(start = CoroutineStart.LAZY) {
        withContext(Dispatchers.IO) {
            oldTimeMills = System.currentTimeMillis()
            while (_customTimerDuration.value!! > ZERO_INTERVAL) {
                val delayMills = System.currentTimeMillis() - oldTimeMills
                if (delayMills == TICK_INTERVAL) {
                    _customTimerDuration.postValue(_customTimerDuration.value!! - delayMills)
                    oldTimeMills = System.currentTimeMillis()
                }
            }
        }
    }
    val time: LiveData<String> = _time


    companion object {
        private const val MIllIS_IN_FUTURE = 1800000L
        private const val TICK_INTERVAL = 1000L
        private const val MINUTE_UNIT = 60000L
        private const val SECOND_UNIT = 1000L
        private const val ZERO_INTERVAL = 0L
    }
}