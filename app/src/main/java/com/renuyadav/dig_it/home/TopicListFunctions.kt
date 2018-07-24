package com.renuyadav.dig_it.home

import com.renuyadav.dig_it.createtopic.Topic
import io.reactivex.Observable

interface TopicListFunctions {
    fun getTopicList(): Observable<List<Topic>>

    fun updateTopic(index:Int, topic: Topic)
    fun getTopicIndex(topic: Topic):Int
    fun getListSize(): Int
    fun getTopicAt(index: Int): Topic?
    fun addTopicAt(index: Int,topic: Topic)
    fun getNewTopicAdded() :Observable<Topic>
}