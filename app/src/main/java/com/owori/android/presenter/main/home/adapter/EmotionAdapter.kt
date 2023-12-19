package com.owori.android.presenter.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.owori.android.databinding.ItemEmotionBinding
import com.owori.android.presenter.model.EmotionItem

class EmotionAdapter(private val clickEvent: (emotionItem: EmotionItem) -> Unit = {}) : ListAdapter<EmotionItem, EmotionAdapterViewHolder>(EmotionItemDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmotionAdapterViewHolder {
        val binding = ItemEmotionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EmotionAdapterViewHolder(binding, clickEvent)
    }

    override fun onBindViewHolder(holder: EmotionAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class EmotionAdapterViewHolder(private val binding: ItemEmotionBinding, private val clickEvent: (emotionItem: EmotionItem) -> Unit = {}) : RecyclerView.ViewHolder(binding.root) {
    fun bind(emotionItem: EmotionItem) {
        with(binding) {
            data = emotionItem
            emotionItemLayout.setOnClickListener { clickEvent(emotionItem) }
        }
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


