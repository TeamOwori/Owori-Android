package com.owori.android.auth.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.owori.android.auth.ui.view.OnBoardingItemFragment

class OnBoardingViewPagerAdapter(fragment: Fragment, fragmentIdList: List<OnBoardingItemFragment>): FragmentStateAdapter(fragment) {
    private val fragmentList = fragmentIdList;
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}