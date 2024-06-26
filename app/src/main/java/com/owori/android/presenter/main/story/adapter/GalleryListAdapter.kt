package com.owori.android.presenter.main.story.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemGalleryBinding
import com.owori.android.databinding.ItemGalleryHeaderBinding
import com.owori.android.presenter.model.PostData

class GalleryListAdapter(private val onClickPost: (postData: PostData) -> Unit = {}) :
    ListAdapter<Any, CommonViewHolder>(galleryListDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonViewHolder {
        return when (viewType) {
            TYPE_HEADER ->
                CommonViewHolder.GalleryHeaderViewHolder(
                    ItemGalleryHeaderBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )

            else -> CommonViewHolder.GalleryItemViewHolder(
                onClickPost,
                ItemGalleryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is String -> TYPE_HEADER
        else -> TYPE_ITEM
    }


    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1

        private val galleryListDiffUtil = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return when {
                    oldItem is String && newItem is String -> oldItem as String == newItem as String
                    oldItem is PostData && newItem is PostData -> oldItem.id == newItem.id
                    else -> false
                }
            }
        }
    }
}

sealed class CommonViewHolder(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: Any)

    class GalleryHeaderViewHolder(
        private val binding: ItemGalleryHeaderBinding
    ) : CommonViewHolder(binding) {
        override fun bind(item: Any) {
            with(binding) {
                data = item as String
            }
        }
    }

    class GalleryItemViewHolder(
        private val onClickEvent: (postData: PostData) -> Unit = {},
        private val binding: ItemGalleryBinding
    ) : CommonViewHolder(binding) {
        override fun bind(item: Any) {
            with(binding) {
                data = item as PostData
                itemLayout.setOnClickListener {
                    onClickEvent(item)
                }
            }
        }
    }
}

