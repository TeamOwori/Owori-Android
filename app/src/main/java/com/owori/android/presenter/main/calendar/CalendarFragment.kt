package com.owori.android.presenter.main.calendar

import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalendarFragment :
    BaseFragment<FragmentCalendarBinding, CalendarViewModel>(R.layout.fragment_calendar) {
    override val viewModel: CalendarViewModel by viewModels()
    override fun setBindingVariables() {}

    override fun initView() {}

    override fun initObserver() {}
}