package com.owori.android.presenter.main.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.databinding.FragmentHomeBinding
import com.owori.android.presenter.main.home.adapter.DdayAdapter
import com.owori.android.presenter.main.home.adapter.FamilyMemberAdapter
import com.owori.android.presenter.main.home.adapter.FamilyMemberWordAdapter
import com.owori.android.presenter.main.home.adapter.FamilyPhotoAdapter
import com.owori.android.presenter.util.SnapPagerScrollListener
import com.owori.android.presenter.util.SnapPagerScrollListener.Companion.ON_SCROLL
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModels()
    private val emotionListAdapter: FamilyMemberAdapter by lazy { FamilyMemberAdapter() }
    private val dDayListAdapter: DdayAdapter by lazy {
        DdayAdapter { id ->
            viewModel.deleteDdayItem(
                id
            )
        }
    }
    private val familyPhotoAdapter: FamilyPhotoAdapter by lazy { FamilyPhotoAdapter() }
    private val dDaySnapHelper = PagerSnapHelper()
    private val dDayScrollListener = SnapPagerScrollListener(
        dDaySnapHelper,
        ON_SCROLL,
        true,
        object : SnapPagerScrollListener.OnChangeListener {
            override fun onSnapped(position: Int) {}
        }
    )
    private val familyPhotoSnapHelper = PagerSnapHelper()
    private val familyPhotoScrollListener = SnapPagerScrollListener(
        familyPhotoSnapHelper,
        ON_SCROLL,
        true,
        object : SnapPagerScrollListener.OnChangeListener {
            override fun onSnapped(position: Int) {}
        }
    )
    private val familyMemberWordAdapter: FamilyMemberWordAdapter by lazy { FamilyMemberWordAdapter() }

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
            familyPhotoList.observe(viewLifecycleOwner) {
                familyPhotoAdapter.submitList(it)
            }
            familyMemberList.observe(viewLifecycleOwner) {
                familyMemberWordAdapter.submitList(it)
            }
        }
    }

    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        initEmotionRecyclerView()
        initDdayRecyclerView()
        initFamilyPhotoRecyclerView()
        initFamilyMemberWordRecyclerView()
    }

    private fun initFamilyPhotoRecyclerView() {
        with(binding.familyPhotoRecyclerView) {
            familyPhotoSnapHelper.attachToRecyclerView(this)
            adapter = familyPhotoAdapter
            addOnScrollListener(familyPhotoScrollListener)
        }
    }

    private fun initDdayRecyclerView() {
        with(binding.dDayRecyclerView) {
            dDaySnapHelper.attachToRecyclerView(this)
            adapter = dDayListAdapter
            addOnScrollListener(dDayScrollListener)
        }
    }

    private fun initEmotionRecyclerView() {
        binding.emotionRecyclerView.adapter = emotionListAdapter
    }

    private fun initFamilyMemberWordRecyclerView() {
        binding.familyMemberWordRecyclerView.adapter = familyMemberWordAdapter
    }
}