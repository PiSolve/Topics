package com.renuyadav.dig_it.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.renuyadav.dig_it.createtopic.Topic
import com.renuyadav.dig_it.databinding.RowTopicListBinding

class TopicAdapter constructor(val context: Context, var list: MutableList<Topic>) : RecyclerView.Adapter<TopicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding = RowTopicListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicViewHolder(context,binding)
    }


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.bindTo(list[position])
    }

    fun setData(listOfTopics: List<Topic>) {
        list = listOfTopics as MutableList<Topic>
        notifyDataSetChanged()
    }

    fun addNewTopic( it: Topic) {
        Log.d(">>>TopicAdapter","incoming topic  at  current list size  is ${list.size}")
        list.add(it)
        notifyDataSetChanged()

    }
}

class TopicViewHolder constructor(val context: Context, private val binding: RowTopicListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindTo(topic: Topic) {
        binding.topicTitle.text = topic.title
        binding.upvoteCount.text = topic.upVotes.toString()
        binding.root.setOnClickListener { RateTopic.rateATopic(context, topic) }
    }

}
