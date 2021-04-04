package com.android.rickmorty.home_screen.vm

import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.data.models.RickMorty
import com.android.data.remote.RickAndMortyApi
import com.android.rickmorty.commons.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ViewModelCharacters: BaseViewModel() {

    private val _rickAndMortyData = MutableLiveData<ArrayList<RickMorty>>()
    val rickAndMortyData: LiveData<ArrayList<RickMorty>>
        get() = _rickAndMortyData

    val dataAllList = arrayListOf<RickMorty>()

    val loadPage = MutableLiveData<Boolean>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        coroutineScope.launch {
            getData()
        }
    }

    private suspend fun getData() {
        _isLoading.value = true
        try {
            val response = RickAndMortyApi.retrofitService.getData()
            if (response.isSuccessful) {
                val dataRickAndMorty = response.body()
                if (dataRickAndMorty!!.results!!.isNotEmpty()) {
                    dataAllList.addAll(dataRickAndMorty.results)
                    _rickAndMortyData.value = dataAllList
                }
                _isLoading.postValue(false)
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