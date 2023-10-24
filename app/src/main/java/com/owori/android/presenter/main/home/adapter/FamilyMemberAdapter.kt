package com.owori.android.presenter.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemFamilyMemberBinding
import com.owori.android.presenter.model.EmotionItem

class FamilyMemberAdapter : ListAdapter<EmotionItem, FamilyMemberAdapter.ViewHolder>(MemberDiffUtil()) {

    inner class ViewHolder(private val binding: ItemFamilyMemberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memberItem: EmotionItem) {
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

class MemberDiffUtil : DiffUtil.ItemCallback<EmotionItem>(){
    override fun areItemsTheSame(oldItem: EmotionItem, newItem: EmotionItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: EmotionItem, newItem: EmotionItem): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
