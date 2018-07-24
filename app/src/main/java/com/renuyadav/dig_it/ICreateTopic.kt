package com.renuyadav.dig_it

import com.renuyadav.dig_it.createtopic.Topic
import io.reactivex.Observable

interface ICreateTopic{
    fun newTopicCreated():Observable<Topic>
}