package com.owori.android.auth.ui.view

import androidx.activity.addCallback
import androidx.viewpager2.widget.ViewPager2
import com.owori.android.common.ui.view.BaseFragment
import com.owori.android.R
import com.owori.android.databinding.FragmentOnboardingBinding
import com.owori.android.auth.ui.adapter.OnBoardingViewPagerAdapter
import com.owori.android.auth.ui.viewmodel.OnBoardingViewModel
import com.owori.android.common.navigateTo
import org.koin.android.ext.android.inject

class OnBoardingFragment :
    BaseFragment<FragmentOnboardingBinding, OnBoardingViewModel>(R.layout.fragment_onboarding) {
    override val viewModel: OnBoardingViewModel by inject()
    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initView() {
        initViewPager()
        initCustomOnBackPressed()
    }

    override fun initObserver() {
        with(viewModel) {
            finishOnBoarding.observe(viewLifecycleOwner) {
                navigateTo(R.id.action_onboardingFragment_to_loginFragment)
            }

            currentItemIndex.observe(viewLifecycleOwner) {
                binding.onboardingViewpager.setCurrentItem(it, true)
            }
        }
    }

    private fun initCustomOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    private fun initViewPager() {
        with(binding) {
            val viewpagerFragmentAdapter =
                OnBoardingViewPagerAdapter(this@OnBoardingFragment, setOnBoardingList())
            onboardingViewpager.adapter = viewpagerFragmentAdapter
            registerOnPageChangeCallback()
        }
    }

    private fun setOnBoardingList() = listOf(
        OnBoardingItemFragment(R.layout.fragment_onboarding_first),
        OnBoardingItemFragment(R.layout.fragment_onboarding_second),
        OnBoardingItemFragment(R.layout.fragment_onboarding_third),
    )

    private fun registerOnPageChangeCallback() {
        binding.onboardingViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.setCurrentItemIndex(position)
            }
        })
    }
}