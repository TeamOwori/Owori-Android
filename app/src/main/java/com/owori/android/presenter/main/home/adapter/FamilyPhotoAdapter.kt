package com.owori.android.presenter.main.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemAddFamilyPhotoBinding
import com.owori.android.databinding.ItemFamilyPhotoBinding
import com.owori.android.presenter.model.FamilyPhotoItem

class FamilyPhotoAdapter(private val onClickAddPhoto: () -> Unit = {}) :
    ListAdapter<FamilyPhotoItem, CommonViewHolder>(familyPhotoDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonViewHolder {
        Log.d("viewType", "$viewType")
        return when (viewType) {
            FamilyPhotoItem.FamilyPhotoViewType.PHOTO.ordinal ->
                CommonViewHolder.FamilyPhotoViewHolder(
                    ItemFamilyPhotoBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )

            else -> CommonViewHolder.AddPhotoViewHolder(
                onClickAddPhoto,
                ItemAddFamilyPhotoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int =
        getItem(position).viewType.ordinal


    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val familyPhotoDiffUtil = object : DiffUtil.ItemCallback<FamilyPhotoItem>() {
            override fun areItemsTheSame(oldItem: FamilyPhotoItem, newItem: FamilyPhotoItem): Boolean {
                return oldItem.viewType == newItem.viewType
            }

            override fun areContentsTheSame(oldItem: FamilyPhotoItem, newItem: FamilyPhotoItem): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}

sealed class CommonViewHolder(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: FamilyPhotoItem)

    class FamilyPhotoViewHolder(
        private val binding: ItemFamilyPhotoBinding
    ) : CommonViewHolder(binding) {
        override fun bind(item: FamilyPhotoItem) {
            with(binding) {
                data = item.photoData
            }
        }
    }

    class AddPhotoViewHolder(
        private val onClickEvent: () -> Unit = {},
        private val binding: ItemAddFamilyPhotoBinding
    ) : CommonViewHolder(binding) {
        override fun bind(item: FamilyPhotoItem) {
            with(binding) {
                addPhotoLayout.setOnClickListener {
                    onClickEvent()
                }
            }
        }
    }
}

