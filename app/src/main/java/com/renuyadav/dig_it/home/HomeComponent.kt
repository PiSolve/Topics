package com.renuyadav.dig_it.home

import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(activity: FeedPage)
}