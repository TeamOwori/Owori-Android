package com.owori.android.presenter.main.home

import androidx.fragment.app.viewModels
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.databinding.FragmentHomeBinding
import com.owori.android.presenter.main.home.adapter.DdayAdapter
import com.owori.android.presenter.main.home.adapter.FamilyMemberAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModels()
    private val emotionListAdapter: FamilyMemberAdapter by lazy { FamilyMemberAdapter() }
    private val dDayListAdapter: DdayAdapter by lazy { DdayAdapter { id -> viewModel.deleteDdayItem(id) } }

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
            dDayRecyclerView.adapter = dDayListAdapter
        }
    }
}