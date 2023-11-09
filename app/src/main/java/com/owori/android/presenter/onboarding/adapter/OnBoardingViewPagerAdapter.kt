package com.owori.android.presenter.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.owori.android.presenter.onboarding.OnBoardingItemFragment

class OnBoardingViewPagerAdapter(fragment: Fragment, fragmentIdList: List<OnBoardingItemFragment>): FragmentStateAdapter(fragment) {
    private val fragmentList = fragmentIdList;
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}