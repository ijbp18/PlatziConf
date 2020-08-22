package com.home.platziconf.view.adapter

import com.home.platziconf.model.Speaker

interface SpeakerListener {

    fun OnSpeakerClicked(speaker: Speaker, position:Int)
}
