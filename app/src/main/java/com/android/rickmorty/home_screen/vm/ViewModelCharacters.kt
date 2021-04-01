package com.android.rickmorty.home_screen.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.data.models.RickMorty
import com.android.data.remote.RickAndMortyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ViewModelCharacters: ViewModel() {

    private val _rickAndMortyData = MutableLiveData<ArrayList<RickMorty>>()
    val rickAndMortyData: LiveData<ArrayList<RickMorty>>
        get() = _rickAndMortyData

    val dataAllList = arrayListOf<RickMorty>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        coroutineScope.launch {
            getData()
        }
    }

    private suspend fun getData() {
        try {
            val response = RickAndMortyApi.retrofitService.getData()
            if (response.isSuccessful) {
                val dataRickAndMorty = response.body()
                if (dataRickAndMorty!!.results!!.isNotEmpty()) {
                    dataAllList.addAll(dataRickAndMorty.results)
                    _rickAndMortyData.value = dataAllList
                }
            } else {

            }

        } catch (e: UnknownHostException) {
            //No hay conexión a Internet o el host no está disponible

            _rickAndMortyData.value = ArrayList()
        } catch (e: SocketTimeoutException) {
            //Se agota el tiempo de espera

            _rickAndMortyData.value = ArrayList()
        } catch (e: Exception) {

            _rickAndMortyData.value = ArrayList()
        }
    }
}