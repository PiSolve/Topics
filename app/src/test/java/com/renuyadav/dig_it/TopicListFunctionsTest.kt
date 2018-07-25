package com.renuyadav.dig_it

import com.renuyadav.dig_it.createtopic.Topic
import com.renuyadav.dig_it.home.TopicListFunctions
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TopicListFunctionsTest {

    private lateinit var topicList: MutableList<Topic>
    var topic1 = Topic(title = "1", upVotes = 1, id = 1)
    var topic2 = Topic(title = "2", upVotes = 2, id = 2)
    var topic3 = Topic(title = "3", upVotes = 3, id = 3)
    var topic4 = Topic(title = "4", upVotes = 4, id = 4)
    val topic5 = Topic(title = "5", upVotes = 5, id = 5)
    val topic6 = Topic(title = "6", upVotes = 6, id = 6)
    val topic7 = Topic(title = "7", upVotes = 7, id = 7)
    val topic8 = Topic(title = "8", upVotes = 8, id = 8)
    private var topicListFunctions = object : TopicListFunctions {
        override fun getTopicList(): Observable<List<Topic>> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun updateTopic(index: Int, topic: Topic) {
            topicList.removeAt(index)
            topicList.add(index,topic)
        }

        override fun getTopicIndex(topic: Topic): Int {
           return  topicList.indexOf(topic)
        }

        override fun getListSize(): Int {
            return topicList.size
        }

        override fun getTopicAt(index: Int): Topic? {
            return topicList.get(index)
        }

        override fun addTopicAt(index: Int, topic: Topic) {
            topicList.add(index, topic)
        }

        override fun getNewTopicAdded(): Observable<Topic> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    @Before
    fun setUp() {
        topicList = createDummyTopicList()
    }

    private fun createDummyTopicList(): MutableList<Topic> {
        var topicList = mutableListOf<Topic>()
        topicList.add(topic1)
        topicList.add(topic2)
        topicList.add(topic3)
        topicList.add(topic4)
        topicList.add(topic5)
        topicList.add(topic6)
        return topicList
    }

    @Test
    fun test_index_of_topic() {
        Assert.assertEquals(topicListFunctions.getTopicIndex(topic2),1)
    }

    @Test
    fun test_new_added_topic() {
        topicListFunctions.addTopicAt(6,topic7)
        Assert.assertEquals(topicListFunctions.getTopicAt(6),topic7)
    }


    @Test
    fun test_size_topic_list() {
        topicListFunctions.addTopicAt(topicListFunctions.getListSize(),topic8)
        Assert.assertEquals(topicListFunctions.getListSize(),7)
    }


    @Test
    fun test_update_topic_list() {
        val currentIndex = topicListFunctions.getTopicIndex(topic6)
        topic6.upVotes = 10
        topicListFunctions.updateTopic(currentIndex,topic6)
        Assert.assertEquals(topicListFunctions.getTopicAt(currentIndex)?.upVotes,10L)
        Assert.assertNotEquals(topicListFunctions.getTopicAt(currentIndex)?.upVotes,6L)
    }

}