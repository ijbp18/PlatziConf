package com.home.platziconf.view.adapter

import com.home.platziconf.model.Conference


interface ScheduleListener {

    fun OnConferenceClicked(conference: Conference, position:Int)
}
