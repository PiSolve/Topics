package com.renuyadav.dig_it

import com.renuyadav.dig_it.createtopic.CreateATopic
import com.renuyadav.dig_it.home.HomeComponent
import com.renuyadav.dig_it.home.RateTopic
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
public interface AppComponent {
    fun plusHomeComponent(): HomeComponent

    fun inject(activity: CreateATopic)
    fun inject(application: TopicApplication)
    fun inject(rateTopic: RateTopic)

}