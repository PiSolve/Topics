package com.renuyadav.dig_it

import android.util.Log
import com.renuyadav.dig_it.createtopic.Topic
import com.renuyadav.dig_it.home.TopicListFunctions
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author renuYadav
 * This is my favourite class .
 * this class stores a list of all topics and emits new time based on request (when new topic added ,
 * when list is sorted (as upvotes would have been changed)
 */
@Singleton
class TopicList @Inject constructor() : TopicListFunctions {
    var topicList = emptyList<Topic>().toMutableList()
    var topicListObservable = PublishSubject.create<List<Topic>>()
    var newtopicObservable = PublishSubject.create<Topic>()

    override fun getTopicList(): Observable<List<Topic>> {
        return topicListObservable
    }

    /**
     * this is for adding an updated topic to list ,
     * remove earlier one , and add new one and then sort and emit
     */
    override fun updateTopic(index:Int, topic: Topic) {
        Log.d(">>>TopicLis:updateTopic","addingNewTopic at $index,current size ${topicList.size}")
        topicList.removeAt(index)
        Log.d(">>>TopicLis:updateTopic","removed at $index,current size ${topicList.size}")
        topicList.add(index,topic)
        Log.d(">>>TopicLis:updateTopic","new topic added  at $index,current size ${topicList.size}")
        sortAndEmit()
    }

    private fun sortAndEmit() {
        topicList.sortWith(createComparatorByState())
        topicListObservable.onNext(topicList)
    }

    /**
     * comparator to sort the list based on upvotes
     */
    private fun createComparatorByState(): java.util.Comparator<Topic> {
        return java.util.Comparator<Topic> { firstTopic, secondTopic ->
            val lds = firstTopic.upVotes
            val rds = secondTopic.upVotes
            when {
                lds == rds -> 0
                lds > rds -> +1
                else ->  -1
            }
        }
    }

    /**
     * this is used to add a new topic to list
     */
    override fun getNewTopicAdded(): Observable<Topic>  = newtopicObservable


    override fun getListSize(): Int = topicList.size

    override fun getTopicAt(index: Int): Topic? = if (topicList.size >= index) topicList[index] else null

    override fun addTopicAt(index: Int, topic: Topic) {
        Log.d(">>>TopicList:addTopicAt","AddTopic at $index,current size ${topicList.size}")
        topicList.add(topic)
        sortAndEmit()

    }

    /**
     * this will be used to update the topic at given index.
     */
    override fun getTopicIndex(topic: Topic): Int {
        Log.d(">>>TopicList","returned index is ${topicList.indexOf(topic)}")
        return topicList.indexOf(topic)
    }
}