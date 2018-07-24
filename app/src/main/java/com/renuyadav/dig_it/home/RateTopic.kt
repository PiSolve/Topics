package com.renuyadav.dig_it.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.renuyadav.dig_it.R
import com.renuyadav.dig_it.TopicApplication
import com.renuyadav.dig_it.createtopic.Topic
import com.renuyadav.dig_it.databinding.ActivityRateTopicBinding
import javax.inject.Inject

class RateTopic : AppCompatActivity() {
    lateinit var binding: ActivityRateTopicBinding
    @Inject
    lateinit var topicListFunctions: TopicListFunctions
    lateinit var topic: Topic
    var indexOfTopic:Int = 0

    companion object {
        @JvmStatic
        fun rateATopic(context: Context, topic: Topic) {
            val intent = Intent(context, RateTopic::class.java)
            val bundle = Bundle()
            bundle.putParcelable("EXTRA_DATA_TOPIC", topic)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    fun getDataFromBundle() {
        topic = intent.getParcelableExtra("EXTRA_DATA_TOPIC")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDI()
        getDataFromBundle()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Rate Topic"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rate_topic, null)
        setData()
        setListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_a_topic, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> saveAndFinish()
            R.id.action_done -> saveAndFinish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun saveAndFinish() {
        topicListFunctions.updateTopic(indexOfTopic,topic)
        finish()
    }

    private fun setData() {
        binding.topicTitle.text = topic.title
        binding.upvoteCount.text = topic.upVotes.toString()
        indexOfTopic = topicListFunctions.getTopicIndex(topic)
    }

    private fun setListeners() {
        binding.upvoteTopic.setOnClickListener {
            topic.upVotes++
            binding.upvoteCount.text = topic.upVotes.toString()
        }
        binding.downvoteTopic.setOnClickListener {
            topic.upVotes--
            binding.upvoteCount.text = topic.upVotes.toString()
        }
    }

    private fun setUpDI() {
        (application as TopicApplication).getAppComponent().inject(this)
    }
}