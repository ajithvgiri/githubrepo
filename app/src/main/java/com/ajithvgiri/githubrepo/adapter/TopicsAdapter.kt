package com.ajithvgiri.githubrepo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajithvgiri.githubrepo.databinding.LayoutTopicsBinding

class TopicsAdapter(var topics: List<String>) : RecyclerView.Adapter<TopicsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopicsAdapter.ViewHolder {
        return ViewHolder(
            LayoutTopicsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopicsAdapter.ViewHolder, position: Int) {
        holder.binding.topic = topics[position]
    }

    override fun getItemCount(): Int = topics.size

    inner class ViewHolder(val binding: LayoutTopicsBinding) :
        RecyclerView.ViewHolder(binding.root)
}