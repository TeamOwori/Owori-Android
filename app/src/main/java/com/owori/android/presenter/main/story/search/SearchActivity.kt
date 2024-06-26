package com.owori.android.presenter.main.story.search

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.getColor
import com.owori.android.R
import com.owori.android.core.BaseActivity
import com.owori.android.databinding.ActivitySearchBinding
import com.owori.android.presenter.main.story.detail.DetailActivity
import com.owori.android.presenter.main.story.search.adapter.RecentResultListAdapter
import com.owori.android.presenter.main.story.search.adapter.SearchResultListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity :
    BaseActivity<ActivitySearchBinding, SearchViewModel>(R.layout.activity_search) {
    override val viewModel: SearchViewModel by viewModels()
    private val searchResultListAdapter: SearchResultListAdapter by lazy {
        SearchResultListAdapter { postData ->
            DetailActivity.startActivity(this@SearchActivity, postData)
        }
    }
    private val recentResultListAdapter: RecentResultListAdapter by lazy {
        RecentResultListAdapter({ recentKeyword ->
            viewModel.setSearchKeyword(recentKeyword)
        }, { recentKeyword ->
            viewModel.removeRecentSearchKeyword(recentKeyword)
        })
    }

    override fun setBindingVariables(binding: ActivitySearchBinding) {
        binding.vm = viewModel
    }

    override fun initView() {
        initSearchResultRecyclerView()
        initRecentResultRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        setStatusBarColor(getColor(this, R.color.white))
    }

    override fun initObserver() {
        with(viewModel) {
            finishButtonClicked.observe(this@SearchActivity) {
                finish()
            }
            clearButtonClicked.observe(this@SearchActivity) {
                clearSearchKeyWord()
            }
            recentSearchKeywords.observe(this@SearchActivity) {
                recentResultListAdapter.submitList(it)
            }
            searchResult.observe(this@SearchActivity) {
                searchResultListAdapter.submitList(it)
            }
        }
    }

    private fun initSearchResultRecyclerView() {
        binding.searchResultRecyclerView.adapter = searchResultListAdapter
    }

    private fun initRecentResultRecyclerView() {
        binding.recentSearchRecyclerView.adapter = recentResultListAdapter
    }

    companion object {
        fun startActivity(context: Context) {
            Intent(context, SearchActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}