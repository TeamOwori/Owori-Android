package com.owori.android.presenter.main

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.databinding.ActivityMainBinding
import com.owori.android.presenter.main.calendar.CalendarFragment
import com.owori.android.presenter.main.home.HomeFragment
import com.owori.android.presenter.main.story.StoryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val viewModel : MainViewModel by viewModels()
    private val homeFragment by lazy { HomeFragment() }
    private val calendarFragment by lazy { CalendarFragment() }
    private val storyFragment by lazy { StoryFragment() }

    override fun initView() {
        initBottomNavigation()
        Log.d("DAOUTECH", "hello: world")
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.apply {
            itemIconTintList = null
            setOnItemSelectedListener { _menuItem ->
                with(binding.bottomNavigation) {
                    when (_menuItem.itemId) {
                        R.id.home -> {
                            setNotSelectedIcon()
                            _menuItem.setIcon(R.drawable.icon_home_selected)
                            changeFragment(homeFragment)
                        }

                        R.id.calendar -> {
                            setNotSelectedIcon()
                            _menuItem.setIcon(R.drawable.icon_calendar_selected)
                            changeFragment(calendarFragment)
                        }

                        R.id.story -> {
                            setNotSelectedIcon()
                            _menuItem.setIcon(R.drawable.icon_story_selected)
                            changeFragment(storyFragment)
                        }
                    }
                }
                true
            }
            selectedItemId = R.id.home
        }
    }

    private fun setNotSelectedIcon() {
        with (binding.bottomNavigation) {
            menu.findItem(R.id.story).setIcon(R.drawable.icon_story)
            menu.findItem(R.id.home).setIcon(R.drawable.icon_home)
            menu.findItem(R.id.calendar).setIcon(R.drawable.icon_calendar)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }

    override fun initObserver() {}

    override fun setBindingVariables(binding: ActivityMainBinding) {}
    companion object {
        fun startActivity(context: Context) {
            Intent(context, MainActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}