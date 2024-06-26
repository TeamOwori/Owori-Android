package com.owori.android.presenter.main.story.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemDetailPhotoBinding
import com.owori.android.databinding.ItemFamilyPhotoBinding
import com.owori.android.presenter.model.PhotoData

class DetailPhotoAdapter(private val onClickAddPhoto: () -> Unit = {}) :
    ListAdapter<PhotoData, DetailPhotoViewHolder>(detailPhotoDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailPhotoViewHolder =
        DetailPhotoViewHolder(
            ItemDetailPhotoBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    override fun onBindViewHolder(holder: DetailPhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        private val detailPhotoDiffUtil = object : DiffUtil.ItemCallback<PhotoData>() {
            override fun areItemsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}

class DetailPhotoViewHolder(
    private val binding: ItemDetailPhotoBinding,
    private val onClickEvent: () -> Unit = {}
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PhotoData) {
        with(binding) {
            data = item
            itemLayout.setOnClickListener {
                onClickEvent()
            }
        }
    }
}
