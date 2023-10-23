package com.owori.android.presenter.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemFamilyMemberBinding
import com.owori.android.presenter.model.MemberItem

class FamilyMemberAdapter : ListAdapter<MemberItem, FamilyMemberAdapter.ViewHolder>(MemberDiffUtil()) {

    inner class ViewHolder(private val binding: ItemFamilyMemberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memberItem: MemberItem) {
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

class MemberDiffUtil : DiffUtil.ItemCallback<MemberItem>(){
    override fun areItemsTheSame(oldItem: MemberItem, newItem: MemberItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: MemberItem, newItem: MemberItem): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
