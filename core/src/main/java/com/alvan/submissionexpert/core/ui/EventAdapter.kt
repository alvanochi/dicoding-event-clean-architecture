package com.alvan.submissionexpert.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alvan.submissionexpert.core.databinding.ItemListEventBinding
import com.alvan.submissionexpert.core.domain.model.Event
import com.bumptech.glide.Glide

class EventAdapter : ListAdapter<Event, EventAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Event) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemListEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val itemEvent = getItem(position)
        holder.bind(itemEvent)
    }

    inner class ListViewHolder(private var binding: ItemListEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listEvent: Event) {
            Glide.with(itemView.context)
                .load(listEvent.mediaCover)
                .into(binding.ivItemImage)
            binding.tvItemTitle.text = listEvent.name
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Event> =
            object : DiffUtil.ItemCallback<Event>() {
                override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
                    return oldItem == newItem
                }
            }
    }
}