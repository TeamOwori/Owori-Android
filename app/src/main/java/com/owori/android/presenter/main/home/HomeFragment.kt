package com.owori.android.presenter.main.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.databinding.FragmentHomeBinding
import com.owori.android.presenter.main.home.adapter.DdayAdapter
import com.owori.android.presenter.main.home.adapter.FamilyMemberAdapter
import com.owori.android.presenter.util.SnapPagerScrollListener
import com.owori.android.presenter.util.SnapPagerScrollListener.Companion.ON_SCROLL
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModels()
    private val emotionListAdapter: FamilyMemberAdapter by lazy { FamilyMemberAdapter() }
    private val dDayListAdapter: DdayAdapter by lazy { DdayAdapter { id -> viewModel.deleteDdayItem(id) } }
    private val snapHelper = PagerSnapHelper()
    private val listener = SnapPagerScrollListener(
        snapHelper,
        ON_SCROLL,
        true,
        object : SnapPagerScrollListener.OnChangeListener {
            override fun onSnapped(position: Int) {
                //position 받아서 이벤트 처리
            }
        }
    )

    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initObserver() {
        with(viewModel) {
            familyEmotionList.observe(viewLifecycleOwner) {
                emotionListAdapter.submitList(it)
            }
            dDayList.observe(viewLifecycleOwner) {
                dDayListAdapter.submitList(it)
            }
        }
    }

    override fun initView() {
        with(binding) {
            emotionRecyclerView.adapter = emotionListAdapter
            snapHelper.attachToRecyclerView(dDayRecyclerView)
            dDayRecyclerView.adapter = dDayListAdapter
            dDayRecyclerView.addOnScrollListener(listener)
        }
    }
}