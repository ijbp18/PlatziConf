package com.home.platziconf.view.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.home.platziconf.R
import com.home.platziconf.model.Conference
import com.home.platziconf.view.adapter.ScheduleAdapter
import com.home.platziconf.view.adapter.ScheduleListener
import com.home.platziconf.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment(), ScheduleListener {

    private lateinit var  scheduleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //
        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        //llamamos los datos
        viewModel.refresh()

        //Crear instancia del adapter del recyclerView
        scheduleAdapter = ScheduleAdapter(this)

        //aplicamos algunos cambios a nuestro recycler
        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = scheduleAdapter
        }

        //Observar los datos
        observerViewModel()

    }

    private fun observerViewModel() {
        viewModel.listSchedule.observe(this, Observer<List<Conference>>{ schedule ->
            scheduleAdapter.updteData(schedule)
        })

        viewModel.isLoading.observe(this, Observer<Boolean> {
            if(it != null)
                rlBaseSchedule.visibility = View.INVISIBLE
        })
    }

    override fun OnConferenceClicked(conference: Conference, position: Int) {
        //Creamos un bundle para enviarlo a la otra vista
        val bundle = bundleOf("conference" to conference)
        //Y ordenamos la navegacion al detalle necesario junto con los datos seleccionados
        findNavController().navigate(R.id.scheduleDetailFragmentDialog, bundle)
    }


}
