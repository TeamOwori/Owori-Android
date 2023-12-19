package com.owori.android.presenter.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemNoticeBinding
import com.owori.android.presenter.model.NoticeData

class NoticeAdapter(private val clickEvent: (id: Int) -> Unit = {}) : ListAdapter<NoticeData, NoticeAdapterViewHolder>(NoticeDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoticeAdapterViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoticeAdapterViewHolder(binding, clickEvent)
    }

    override fun onBindViewHolder(holder: NoticeAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NoticeAdapterViewHolder(private val binding: ItemNoticeBinding, private val clickEvent: (id: Int) -> Unit = {}) : RecyclerView.ViewHolder(binding.root) {
    fun bind(dDayData: NoticeData) {
        with(binding) {
            data = dDayData
        }
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
