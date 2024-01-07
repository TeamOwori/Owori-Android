package com.owori.android.presenter.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemFamilyMemberWordBinding
import com.owori.android.presenter.model.FamilyMemberData

class FamilyMemberWordAdapter : ListAdapter<FamilyMemberData, FamilyMemberWordAdapter.ViewHolder>(
    familyMemberWordDiffUtil
) {

    inner class ViewHolder(private val binding: ItemFamilyMemberWordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(familyMemberData: FamilyMemberData) {
            binding.data = familyMemberData
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemFamilyMemberWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val familyMemberWordDiffUtil = object : DiffUtil.ItemCallback<FamilyMemberData>() {
            override fun areItemsTheSame(
                oldItem: FamilyMemberData,
                newItem: FamilyMemberData
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: FamilyMemberData,
                newItem: FamilyMemberData
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}
