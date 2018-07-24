package com.renuyadav.dig_it.createtopic

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Topic(val id: Long, val title: String, var upVotes: Long, var isStarred: Int = 0) : Parcelable

fun Topic.compareTo(topic2: Topic): Int {
    return if (this.upVotes > topic2.upVotes) 1 else 0
}