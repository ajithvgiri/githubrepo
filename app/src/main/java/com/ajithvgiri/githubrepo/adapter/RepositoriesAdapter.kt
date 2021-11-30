package com.ajithvgiri.githubrepo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajithvgiri.githubrepo.api.Repositories
import com.ajithvgiri.githubrepo.databinding.LayoutRepositoryBinding

class RepositoriesAdapter : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {
    var repositories: List<Repositories> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoriesAdapter.ViewHolder {
        return ViewHolder(
            LayoutRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoriesAdapter.ViewHolder, position: Int) {
        val repository = repositories[position]
        holder.binding.repositories = repository
        holder.bind(repository.topics)
    }

    override fun getItemCount(): Int = repositories.size

    inner class ViewHolder(val binding: LayoutRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(topics: List<String>) {
            binding.apply {
                recyclerViewTopics.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = TopicsAdapter(topics)
                }
            }
        }
    }
}