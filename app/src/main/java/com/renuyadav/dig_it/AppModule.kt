package com.renuyadav.dig_it

import com.renuyadav.dig_it.home.TopicListFunctions
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideTopicListFunctions(topicList: TopicList): TopicListFunctions = topicList
    }
}