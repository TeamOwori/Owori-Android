package com.owori.android.auth.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owori.android.common.SingleLiveEvent
import com.owori.android.common.ui.viewmodel.BaseViewModel

class PolicyViewModel : BaseViewModel() {
    private val _currentIndex: MutableLiveData<Int> = MutableLiveData(0)
    val currentIndex: LiveData<Int> = _currentIndex
    private val _finishPolicy: SingleLiveEvent<Unit> = SingleLiveEvent()
    val finishPolicy: LiveData<Unit> = _finishPolicy
    private val _returnLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val returnLogin: LiveData<Unit> = _returnLogin
    private val _indexBack: SingleLiveEvent<Unit> = SingleLiveEvent()
    val indexBack: LiveData<Unit> = _indexBack
    private val _indexForward: SingleLiveEvent<Unit> = SingleLiveEvent()
    val indexForward: LiveData<Unit> = _indexForward
    private val _shareCode: SingleLiveEvent<Unit> = SingleLiveEvent()
    val shareCode: LiveData<Unit> = _shareCode
    private val _checkedAll: SingleLiveEvent<Unit> = SingleLiveEvent()
    val checkedAll: LiveData<Unit> = _checkedAll
    private val _notChecked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val notChecked: LiveData<Unit> = _notChecked
    private val _personalInfoClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val personalInfoClicked: LiveData<Unit> = _personalInfoClicked
    private val _serviceClicked: SingleLiveEvent<Unit> = SingleLiveEvent()
    val serviceClicked: LiveData<Unit> = _serviceClicked

    val _nickName: MutableLiveData<String> = MutableLiveData()
    val nickName: LiveData<String> = _nickName
    val _birthDate: MutableLiveData<String> = MutableLiveData("")
    val birthDate: LiveData<String> = _birthDate
    val _groupName: MutableLiveData<String> = MutableLiveData("")
    val groupName: LiveData<String> = _groupName
    val _groupCode: MutableLiveData<String> = MutableLiveData("")
    val groupCode: LiveData<String> = _groupCode
    val _serviceChecked: MutableLiveData<Boolean> = MutableLiveData(false)
    val serviceChecked: LiveData<Boolean> = _serviceChecked
    val _personalInfoChecked: MutableLiveData<Boolean> = MutableLiveData(false)
    val personalInfoChecked: LiveData<Boolean> = _personalInfoChecked

    private var _lastIndex : MutableLiveData<Int> = MutableLiveData(0)
    val lastIndex = _lastIndex

    fun onClickCheckButton() {
        _currentIndex.value?.let { _index ->
            when (_index) {
                in 4..5 -> {
                    _currentIndex.value = LAST_PAGE
                    _lastIndex.value = _index
                    if(personalInfoChecked.value == true && serviceChecked.value == true) {
                        _checkedAll.call()
                    }
                    _indexForward.call()
                }
                LAST_PAGE -> {
                    _finishPolicy.call()
                }
                else -> {
                    _lastIndex.value = _index
                    _currentIndex.value = _currentIndex.value?.plus(1)
                    _indexForward.call()
                }
            }
        }
    }

    fun onClickBackButton() {
        _currentIndex.value?.let { _index ->
            if (_index == 0) _returnLogin.call()
            else {
                _currentIndex.value = lastIndex.value
                if(_lastIndex.value == 5) {
                    _lastIndex.value = 2
                } else {
                    _lastIndex.value = _lastIndex.value?.minus(1)
                }
                _indexBack.call()
            }
        }
    }

    fun onClickReceivedCodeButton() {
        _currentIndex.value?.let { _index ->
            _lastIndex.value = 2
            _currentIndex.value = 5
            _indexForward.call()
        }
    }

    fun onClickMakeCodeButton() {
        _lastIndex.value = 2
        _currentIndex.value = 3
        _indexForward.call()
    }

    fun onClickShareCodeButton() {
        _shareCode.call()
    }

    fun onClickCheckBox() {
        if(personalInfoChecked.value == true && serviceChecked.value == true) {
            _checkedAll.call()
        } else {

        }
    }

    fun onClickService() {
        _serviceClicked.call()
    }

    fun onClickPersonalInfo() {
        _personalInfoClicked.call()
    }

    fun setCurrentItemIndex(index: Int) {
        _currentIndex.value = index
    }

    companion object {
        private const val LAST_PAGE = 6
    }

}