package com.owori.android.presenter.main.story.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemPostBinding
import com.owori.android.presenter.model.PostData

class SearchResultListAdapter(private val clickEvent: (postData: PostData) -> Unit = {}) :
    ListAdapter<PostData, PostListAdapterViewHolder>(
        postDiffUtil
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostListAdapterViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostListAdapterViewHolder(binding, clickEvent)
    }

    override fun onBindViewHolder(holder: PostListAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val postDiffUtil = object : DiffUtil.ItemCallback<PostData>() {
            override fun areItemsTheSame(oldItem: PostData, newItem: PostData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PostData, newItem: PostData): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }

    }
}

class PostListAdapterViewHolder(
    private val binding: ItemPostBinding,
    private val clickEvent: (postData: PostData) -> Unit = {}
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(postData: PostData) {
        with(binding) {
            data = postData
            postInfoTextView.text = "좋아요  ${postData.likeCount}   댓글  ${postData.commentList?.size ?: 0}   · ${postData.author}"
            itemPostLayout.setOnClickListener {
                clickEvent(postData)
            }
        }
    }
}