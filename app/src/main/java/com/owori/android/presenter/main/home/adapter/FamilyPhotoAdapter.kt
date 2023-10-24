package com.owori.android.presenter.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemFamilyPhotoBinding
import com.owori.android.presenter.model.FamilyPhotoData

class FamilyPhotoAdapter : ListAdapter<FamilyPhotoData, FamilyPhotoAdapter.ViewHolder>(FamilyPhotoDiffUtil()) {

    inner class ViewHolder(private val binding: ItemFamilyPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(familyPhotoData: FamilyPhotoData) {
            with(binding) {
                data = familyPhotoData
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemFamilyPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class FamilyPhotoDiffUtil : DiffUtil.ItemCallback<FamilyPhotoData>(){
    override fun areItemsTheSame(oldItem: FamilyPhotoData, newItem: FamilyPhotoData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FamilyPhotoData, newItem: FamilyPhotoData): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}