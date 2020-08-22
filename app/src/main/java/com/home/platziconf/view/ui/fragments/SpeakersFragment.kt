package com.home.platziconf.view.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.home.platziconf.R
import com.home.platziconf.model.Speaker
import com.home.platziconf.view.adapter.SpeakerAdapter
import com.home.platziconf.view.adapter.SpeakerListener
import com.home.platziconf.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

class SpeakersFragment : Fragment(), SpeakerListener {

    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        //llamamos los datos
        viewModel.refresh()

        //Crear instancia del adapter del recyclerView
        speakerAdapter = SpeakerAdapter(this)

        //aplicamos algunos cambios a nuestro recycler
        rvSpeakers.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = speakerAdapter
        }

        //Observar los datos
        observerViewModel()


    }

    private fun observerViewModel() {
        viewModel.listSpeaker.observe(this, Observer<List<Speaker>>{ schedule ->
            speakerAdapter.updteData(schedule)
        })

        viewModel.isLoading.observe(this, Observer<Boolean> {
            if(it != null)
                rlBaseSpeaker.visibility = View.INVISIBLE
        })
    }

    override fun OnSpeakerClicked(speaker: Speaker, position: Int) {
        //Creamos un bundle para enviarlo a la otra vista
        val bundle = bundleOf("speaker" to speaker)
        //Y ordenamos la navegacion al detalle necesario junto con los datos seleccionados
        findNavController().navigate(R.id.speakerDetailFragmentDialog, bundle)
    }


}
