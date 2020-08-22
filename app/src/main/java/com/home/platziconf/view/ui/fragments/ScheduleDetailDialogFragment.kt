package com.home.platziconf.view.ui.fragments


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.home.platziconf.R
import com.home.platziconf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 */
class ScheduleDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullscreenDialogStyle)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toobarConference.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toobarConference.setTitleTextColor(Color.WHITE)
        toobarConference.setNavigationOnClickListener{
            dismiss()
        }
        val conference = arguments?.getSerializable("conference") as Conference
        toobarConference.setTitle(conference.title)

        tvItemScheduleConferenceTitleName.text = conference.title
        val patternDate = "dd/MM/yyyy HH:mm a"
        val simpleDF = SimpleDateFormat(patternDate)
        val date = simpleDF.format(conference.datetime)
        tvDetailConferenceHour.text = date
        tvDetailConferenceSpeaker.text = conference.speaker

        tvDetailConferenceTag.text = conference.tag
        tvDetailConferenceDescription.text = conference.description

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }


}
