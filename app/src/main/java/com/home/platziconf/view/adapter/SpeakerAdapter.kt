package com.home.platziconf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.home.platziconf.R
import com.home.platziconf.model.Speaker
import kotlin.collections.ArrayList

class SpeakerAdapter(var speakerListener: SpeakerListener): RecyclerView.Adapter<SpeakerAdapter.ViewHolder>(){

    var listSpeaker = ArrayList<Speaker>()

    //Cuales el archivo que necesitamos utilizar para el diseño del item del recyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent, false))

    override fun getItemCount() = listSpeaker.size

    override fun onBindViewHolder(holder: SpeakerAdapter.ViewHolder, position: Int) {
        val speaker = listSpeaker[position] as Speaker

        holder.tvSpeakerName.text = speaker.name
        holder.tvSpeakerIJob.text = speaker.workplace
        Glide.with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.ivSpeakerImage)

        holder.itemView.setOnClickListener{
            speakerListener.OnSpeakerClicked(speaker, position)
        }
    }

    fun updteData(data: List<Speaker>){
        listSpeaker.clear()
        listSpeaker.addAll(data)
        notifyDataSetChanged()

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val ivSpeakerImage = itemView.findViewById<ImageView>(R.id.ivItemSpeakerConferenceImage)
        val tvSpeakerName = itemView.findViewById<TextView>(R.id.tvItemSpeakerConferenceName)
        val tvSpeakerIJob= itemView.findViewById<TextView>(R.id.tvItemSpeakerConferenceJob)
    }

}