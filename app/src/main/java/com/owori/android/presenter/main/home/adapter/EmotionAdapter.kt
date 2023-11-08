package com.owori.android.presenter.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemEmotionBinding
import com.owori.android.presenter.model.EmotionItem

class EmotionAdapter(private val clickEvent: (emotionItem: EmotionItem) -> Unit = {}) : ListAdapter<EmotionItem, EmotionAdapter.ViewHolder>(EmotionItemDiffUtil()) {

    inner class ViewHolder(private val binding: ItemEmotionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(emotionItem: EmotionItem) {
            with(binding) {
                data = emotionItem
                emotionItemLayout.setOnClickListener { clickEvent(emotionItem) }
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemEmotionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class EmotionItemDiffUtil : DiffUtil.ItemCallback<EmotionItem>(){
    override fun areItemsTheSame(oldItem: EmotionItem, newItem: EmotionItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EmotionItem, newItem: EmotionItem): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
