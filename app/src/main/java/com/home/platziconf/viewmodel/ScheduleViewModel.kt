package com.home.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.home.platziconf.model.Conference
import com.home.platziconf.network.Callback
import com.home.platziconf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel: ViewModel(){
    val firestoreService = FirestoreService()
    //Variable que almacera los datos actualizados
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    //variable booleana que nos va a permitir actualizar nuestra IU de carga
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }

    private fun getScheduleFromFirebase() {

        firestoreService.getSchedule(object : Callback<List<Conference>>{
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished(){
        //esta funcion nos permite saber en que momento termina un
        // proceso ya sea exitoso o fallido y así en la vista ocultar nuestro proceso de carga (ialog)
        isLoading.value = true
    }
}