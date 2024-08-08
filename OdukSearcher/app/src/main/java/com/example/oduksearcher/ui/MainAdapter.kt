package com.example.oduksearcher.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(private val onClick: (ListItem) -> Unit) :
    ListAdapter<ListItem, RecyclerView.ViewHolder>(object :
        DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return when {
                oldItem is ListItem.ImageItem && newItem is ListItem.ImageItem -> oldItem.uuid == newItem.uuid
                oldItem is ListItem.VideoItem && newItem is ListItem.VideoItem -> oldItem.uuid == newItem.uuid
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}