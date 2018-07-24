package com.renuyadav.dig_it

import android.app.Application
import android.content.res.Configuration

class TopicApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        setUpDagger()
    }

    private fun setUpDagger() {
        component = DaggerAppComponent.builder().build()
        component.inject(this)

    }

    public fun getAppComponent(): AppComponent {
        return component
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }

}