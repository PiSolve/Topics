package com.renuyadav.dig_it.createtopic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.renuyadav.dig_it.R
import com.renuyadav.dig_it.TopicApplication
import com.renuyadav.dig_it.home.TopicListFunctions
import javax.inject.Inject

/**
 * @author renuYadav
 * This Activity will be used to create a new topic and add it to the list
 */
class CreateATopic : AppCompatActivity() {
    @Inject
    lateinit var listFxns: TopicListFunctions

    companion object {
        @JvmStatic
        fun startATopic(context: Context) {
            context.startActivity(Intent(context, CreateATopic::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDI()
        setContentView(R.layout.activity_create_a_topic)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create New Topic"
        listentForMaxLengthReached()
    }

    private fun setUpDI() {
        (application as TopicApplication).getAppComponent().inject(this)
    }

    /**
     * Show a toast of maximum length reached  when length is 255
     */
    private fun listentForMaxLengthReached() {
        findViewById<EditText>(R.id.topic_edit_text).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.run {
                    if (length >= 255) {
                        Toast.makeText(this@CreateATopic, "max length reached", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_a_topic, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_done) {
            saveTopicAndExit()

        } else if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * even if user , dont choose done and press back , save the topic and exit #MAH LIFE  MAH RULEzzzzz
     */
    private fun saveTopicAndExit() {
        val text = findViewById<EditText>(R.id.topic_edit_text).text.toString()
        val index = listFxns.getListSize().toLong()
        var topic = Topic(title = text, upVotes = 0, id = index)
        listFxns.addTopicAt(index.toInt(), topic)
        finish()
    }
}