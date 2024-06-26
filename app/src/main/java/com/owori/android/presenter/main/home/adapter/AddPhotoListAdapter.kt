package com.owori.android.presenter.main.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemAddStoryPhotoBinding
import com.owori.android.presenter.model.PhotoData

class AddPhotoListAdapter(private val onClickPhotoDeleteItem: (id: Int) -> Unit = {} ) : ListAdapter<PhotoData, AddPhotoListAdapterViewHolder>(photoDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddPhotoListAdapterViewHolder {
        val binding = ItemAddStoryPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddPhotoListAdapterViewHolder(binding, onClickPhotoDeleteItem)
    }

    override fun onBindViewHolder(holder: AddPhotoListAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val photoDiffUtil = object : DiffUtil.ItemCallback<PhotoData>(){
            override fun areItemsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}

class AddPhotoListAdapterViewHolder(private val binding: ItemAddStoryPhotoBinding, private val onClickMemberItem: (id: Int) -> Unit = {}) : RecyclerView.ViewHolder(binding.root) {
    fun bind(photo: PhotoData) {
        with(binding) {
            data = photo
            deleteButton.setOnClickListener {
                Log.d("hello", "$data")
                onClickMemberItem(photo.id)
            }
        }
    }
}
