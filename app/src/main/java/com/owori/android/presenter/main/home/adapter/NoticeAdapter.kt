package com.owori.android.presenter.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemNoticeBinding
import com.owori.android.presenter.model.NoticeData

class NoticeAdapter(private val clickEvent: (id: Int) -> Unit = {}) : ListAdapter<NoticeData, NoticeAdapter.ViewHolder>(NoticeDiffUtil()) {

    inner class ViewHolder(private val binding: ItemNoticeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dDayData: NoticeData) {
            with(binding) {
                data = dDayData
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NoticeDiffUtil : DiffUtil.ItemCallback<NoticeData>(){
    override fun areItemsTheSame(oldItem: NoticeData, newItem: NoticeData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoticeData, newItem: NoticeData): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
