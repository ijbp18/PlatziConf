package com.home.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.home.platziconf.model.Speaker
import com.home.platziconf.network.Callback
import com.home.platziconf.network.FirestoreService

class SpeakerViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    //Variable que almacera los datos actualizados
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    //variable booleana que nos va a permitir actualizar nuestra IU de carga
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getSpeakerFromFirebase()
    }

    private fun getSpeakerFromFirebase() {

        firestoreService.getSpeakers(object : Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeaker.postValue(result)
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