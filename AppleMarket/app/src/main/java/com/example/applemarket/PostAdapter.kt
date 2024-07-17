package com.example.applemarket

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.applemarket.databinding.ItemRecyclerviewBinding
import java.text.DecimalFormat

class PostAdapter(val post: MutableList<PostItem>) : RecyclerView.Adapter<PostAdapter.Holder>() {
    fun replaseDecimalFormat(num: Int): String {
        return DecimalFormat("#,###").format(num).toString()
    }

    interface ItemClick {
        fun onClick(view: View, postion: Int)
        fun onPressed(view: View, postion: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Log.d("onBindViewHolder", position.toString())
        holder.itemView.setOnClickListener { itemClick?.onClick(it, position) }
        holder.itemView.setOnLongClickListener { itemClick?.onPressed(it, position); true }
        holder.postImageView.setImageResource(post[position].picture)
        holder.postImageView.clipToOutline = true
        holder.title.text = post[position].title
        holder.address.text = post[position].address
        holder.cost.text = replaseDecimalFormat(post[position].cost) + "원"
        holder.chat.text = post[position].chat.toString()
        holder.like.text = post[position].like.toString()
        if (post[position].isLike) {
            holder.isike.setImageResource(R.drawable.full_heart)
        } else {
            holder.isike.setImageResource(R.drawable.heart)
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return post.size
    }

    inner class Holder(val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // 여기에 필요한 다른 뷰를 추가 가능
        val postImageView = binding.ivItemPic
        val title = binding.tvItemTitle
        val address = binding.tvItemAddress
        val cost = binding.tvItemCost
        val chat = binding.tvChatNum
        val like = binding.tvLikeNum
        val isike = binding.ivItemLike
    }
}
