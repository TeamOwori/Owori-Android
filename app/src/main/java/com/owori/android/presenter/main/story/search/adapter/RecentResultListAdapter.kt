package com.owori.android.presenter.main.story.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemRecentSearchBinding

class RecentResultListAdapter(
    private val clickEvent: (recentKeyword: String) -> Unit = {},
    private val onClickCloseButton: (recentKeyword: String) -> Unit
) :
    ListAdapter<String, RecentResultListAdapterViewHolder>(
        recentResultDiffUtil
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentResultListAdapterViewHolder {
        val binding = ItemRecentSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentResultListAdapterViewHolder(binding, clickEvent, onClickCloseButton)
    }

    override fun onBindViewHolder(holder: RecentResultListAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val recentResultDiffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class RecentResultListAdapterViewHolder(
    private val binding: ItemRecentSearchBinding,
    private val clickEvent: (recentKeyword: String) -> Unit = {},
    private val onClickCloseButton: (recentKeyword: String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(recentSearchKeyword: String) {
        with(binding) {
            data = recentSearchKeyword

            itemLayout.setOnClickListener {
                clickEvent(recentSearchKeyword)
            }

            closeButton.setOnClickListener {
                onClickCloseButton(recentSearchKeyword)
            }
        }
    }
}