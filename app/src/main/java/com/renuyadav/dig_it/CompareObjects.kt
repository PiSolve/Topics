package com.renuyadav.dig_it

import com.renuyadav.dig_it.createtopic.Topic

class CompareObjects {
 
	companion object : Comparator<Topic> {
 
		override fun compare(a: Topic, b: Topic): Int = when {
			a.upVotes >= b.upVotes -> 1
			else -> 0
		}
	}
}