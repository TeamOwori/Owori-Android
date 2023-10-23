package com.owori.android.presenter.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemDDayBinding
import com.owori.android.presenter.model.DdayData

class DdayAdapter(private val clickEvent: (id: Int) -> Unit = {}) : ListAdapter<DdayData, DdayAdapter.ViewHolder>(DdayDiffUtil()) {

    inner class ViewHolder(private val binding: ItemDDayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dDayData: DdayData) {
            with(binding) {
                data = dDayData
                closeButton.setOnClickListener { clickEvent(dDayData.id) }
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemDDayBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DdayDiffUtil : DiffUtil.ItemCallback<DdayData>(){
    override fun areItemsTheSame(oldItem: DdayData, newItem: DdayData): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: DdayData, newItem: DdayData): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
