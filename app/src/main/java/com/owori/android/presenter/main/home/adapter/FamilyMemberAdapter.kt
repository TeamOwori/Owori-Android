package com.owori.android.presenter.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemFamilyMemberBinding
import com.owori.android.presenter.model.ProfileItem

class FamilyMemberAdapter(private val onClickMemberItem: (id: Int) -> Unit = {} ) : ListAdapter<ProfileItem, FamilyMemberAdapterViewHolder>(MemberDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FamilyMemberAdapterViewHolder {
        val binding = ItemFamilyMemberBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FamilyMemberAdapterViewHolder(binding, onClickMemberItem)
    }

    override fun onBindViewHolder(holder: FamilyMemberAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class FamilyMemberAdapterViewHolder(private val binding: ItemFamilyMemberBinding, private val onClickMemberItem: (id: Int) -> Unit = {}) : RecyclerView.ViewHolder(binding.root) {
    fun bind(memberItem: ProfileItem) {
        with(binding) {
            data = memberItem
            memberProfileLayout.setOnClickListener { onClickMemberItem(memberItem.id) }
        }
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
