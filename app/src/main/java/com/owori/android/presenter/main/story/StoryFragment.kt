package com.owori.android.presenter.main.story

import android.util.Log
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.owori.android.R
import com.owori.android.core.BaseFragment
import com.owori.android.databinding.FragmentStoryBinding
import com.owori.android.presenter.main.story.adapter.GalleryListAdapter
import com.owori.android.presenter.main.story.adapter.PostListAdapter
import com.owori.android.presenter.main.story.detail.DetailActivity
import com.owori.android.presenter.main.story.dialog.FilterBottomSheetDialogFragment
import com.owori.android.presenter.main.story.post.PostActivity
import com.owori.android.presenter.main.story.search.SearchActivity
import com.owori.android.presenter.model.Filter.RECENT
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StoryFragment : BaseFragment<FragmentStoryBinding, StoryViewModel>(R.layout.fragment_story) {
    override val viewModel: StoryViewModel by viewModels()

    private val postListAdapter: PostListAdapter by lazy {
        PostListAdapter { postData ->
            DetailActivity.startActivity(requireContext(), postData)
        }
    }

    private val galleryListAdapter: GalleryListAdapter by lazy {
        GalleryListAdapter { postData ->
            DetailActivity.startActivity(requireContext(), postData)
        }
    }

    override fun onResume() {
        super.onResume()
        setStatusBarColor(getColor(requireContext(), R.color.yellow_ffeeb2))
    }

    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initObserver() {
        with(viewModel) {
            postStoryButtonClicked.observe(viewLifecycleOwner) {
                PostActivity.startActivity(requireContext())
            }
            searchButtonClicked.observe(viewLifecycleOwner) {
                SearchActivity.startActivity(requireContext())
            }
            filterLayoutClicked.observe(viewLifecycleOwner) {
                Log.d("filterfilter", "${it}")
                FilterBottomSheetDialogFragment(
                    { viewModel.setCurrentStoryFilter(it) },
                    currentStoryFilter.value ?: RECENT.label
                ).show(childFragmentManager, getString(R.string.dialog_filter))
            }
            postList.observe(viewLifecycleOwner) {
                postListAdapter.submitList(it)
            }
            galleryList.observe(viewLifecycleOwner) {
                Log.d("가나다라","$it")
                galleryListAdapter.submitList(it)
            }
        }
    }

    override fun initView() {
        initListRecyclerView()
        initGalleryRecyclerView()
        initGalleryRefreshLayout()
        initListRefreshLayout()
    }

    private fun initListRecyclerView() {
        binding.listRecyclerView.adapter = postListAdapter
    }

    private fun initGalleryRecyclerView() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (galleryListAdapter.getItemViewType(position) == GalleryListAdapter.TYPE_HEADER) 3 else 1
            }
        }

        with(binding.cardRecyclerView) {
            layoutManager = gridLayoutManager
            adapter = galleryListAdapter
        }
    }

    private fun initGalleryRefreshLayout() {
        with(binding.galleryRefreshLayout) {
            setOnRefreshListener {
                isRefreshing = false
            }
        }
    }

    private fun initListRefreshLayout() {
        with(binding.listRefreshLayout) {
            setOnRefreshListener {
                isRefreshing = false
            }
        }
    }
}