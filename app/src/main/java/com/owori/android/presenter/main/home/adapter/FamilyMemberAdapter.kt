package com.owori.android.presenter.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemFamilyMemberBinding
import com.owori.android.presenter.model.ProfileItem

class FamilyMemberAdapter : ListAdapter<ProfileItem, FamilyMemberAdapter.ViewHolder>(MemberDiffUtil()) {

    inner class ViewHolder(private val binding: ItemFamilyMemberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memberItem: ProfileItem) {
            binding.data = memberItem
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemFamilyMemberBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MemberDiffUtil : DiffUtil.ItemCallback<ProfileItem>(){
    override fun areItemsTheSame(oldItem: ProfileItem, newItem: ProfileItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ProfileItem, newItem: ProfileItem): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
