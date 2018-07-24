package com.renuyadav.dig_it.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.renuyadav.dig_it.R
import com.renuyadav.dig_it.TopicApplication
import com.renuyadav.dig_it.createtopic.CreateATopic
import com.renuyadav.dig_it.createtopic.Topic
import com.renuyadav.dig_it.databinding.ActivityFeedPageBinding
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

const val CREATE_A_TOPIC_REQUEST_CODE = 111

/**
 * @author renuYadav
 * This activity displays the top topics with highest vote first order
 */
class FeedPage : AppCompatActivity() {

    @Inject
    lateinit var topicListFunctions: TopicListFunctions
    lateinit var adapter: TopicAdapter
    private var listOfTopics = mutableListOf<Topic>()
    lateinit var binding: ActivityFeedPageBinding


    companion object {
        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, FeedPage::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDI()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed_page) as ActivityFeedPageBinding
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Popular Topics"
        adapter = TopicAdapter(this, listOfTopics)
        var recyclerView = binding.root.findViewById<RecyclerView>(R.id.topic_list)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        recyclerView.adapter = adapter
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            CreateATopic.startATopic(this@FeedPage)
        }

        listenForTopics()
        listenForNewTopic()
    }

    private fun checkForEmptyList() {
        if (adapter.itemCount == 0) {
            Log.d(">>>FeedPage", "itemcount is 0 ")
            binding.root.findViewById<LinearLayout>(R.id.empty_list_container).visibility = View.VISIBLE
            binding.root.findViewById<RecyclerView>(R.id.topic_list).visibility = View.GONE
        } else {
            Log.d(">>>FeedPage", "itemcount is ${adapter.itemCount} ")
            binding.root.findViewById<LinearLayout>(R.id.empty_list_container).visibility = View.GONE
            binding.root.findViewById<RecyclerView>(R.id.topic_list).visibility = View.VISIBLE
        }
    }

    /**
     * subscribe to any new topic added
     */
    private fun listenForNewTopic() {
        topicListFunctions.getNewTopicAdded()
                .subscribe({
                    it?.run {
                        Log.d(">>>TopicListHome", "new topic is $it")
                        adapter.addNewTopic(it)
                        checkForEmptyList()
                    }
                }, {
                    Log.d(">>>TopicListHome:", "$it")
                })
    }

    /**
     * subscribe to all the topics in memory
     */
    private fun listenForTopics() {
        topicListFunctions.getTopicList()
                .subscribe({
                    it?.run {
                        Log.d(">>>TopicListHome", "new list is $it")
                        listOfTopics = it as MutableList<Topic>
                        adapter.setData(listOfTopics)
                        checkForEmptyList()
                    }
                }, {
                    Log.d(">>>TopicListHome:", "$it")
                })
    }


    /**
     * inject ListOfTopicFunctions
     */
    private fun setUpDI() {
        (application as TopicApplication).getAppComponent()
                .plusHomeComponent()
                .inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
