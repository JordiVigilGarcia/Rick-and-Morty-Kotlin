package com.android.rickmorty.home_screen.vm

import android.app.Activity
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.data.models.RickMorty
import com.android.data.remote.RickMortyAPI
import com.android.rickmorty.commons.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ViewModelCharacters: BaseViewModel() {

    private val _rickMortyResults = MutableLiveData<ArrayList<RickMorty>>()
    val rickMortyResults: LiveData<ArrayList<RickMorty>>
        get() = _rickMortyResults

    val resultAPI = arrayListOf<RickMorty>()

    private var viewModel = Job()

    private val coroutine = CoroutineScope(viewModel + Dispatchers.Main)

    init {
        coroutine.launch {
            apiResults()
        }
    }

    //Retrieve API results

    private suspend fun apiResults() {
        _isLoading.value = true
        try {
            val response = RickMortyAPI.retrofitService.getData()
            if (response.isSuccessful) {
                val dataRickAndMorty = response.body()
                if (dataRickAndMorty!!.results!!.isNotEmpty()) {
                    resultAPI.addAll(dataRickAndMorty.results)
                    _rickMortyResults.value = resultAPI
                }
                _isLoading.postValue(false)
            } else {
                _isLoading.postValue(true)
            }

        } catch (e: SocketTimeoutException) {
            _rickMortyResults.value = ArrayList()
        } catch (e: Exception) {
            _rickMortyResults.value = ArrayList()
        } catch (e: UnknownHostException) {
            _rickMortyResults.value = ArrayList()
        }
    }

    // --> This for loading characters one same recyclerview with different pages <--

    // val loadPage = MutableLiveData<Boolean>()

    //private suspend fun getData(page: Int) {
    //        _isLoading.value = true
    //        try {
    //            val response = RickMortyAPI.retrofitService.getData(page)  <- For this change the RickMortyAPI
    //            if (response.isSuccessful) {
    //                val dataRickAndMorty = response.body()
    //                if (dataRickAndMorty!!.results!!.isNotEmpty()) {
    //                    resultAPI.addAll(dataRickAndMorty.results)
    //                    _rickMortyResults.value = resultAPI
    //                }
    //                _isLoading.postValue(false)
    //            } else {
    //                _isLoading.postValue(true)
    //            }
    //
    //        } catch (e: SocketTimeoutException) {
    //            _rickMortyResults.value = ArrayList()
    //        } catch (e: Exception) {
    //            _rickMortyResults.value = ArrayList()
    //        } catch (e: UnknownHostException) {
    //            _rickMortyResults.value = ArrayList()
    //        }
    //    }

    // --> This change de RickMortyAPI interface <--

    //interface RickMortyAPI{
        //@GET(BASE_API_SERVICE_RICK_MORTY)
        //suspend fun getData(@Query("page") type: Int):
        //Response<DataRickAndMorty>
    //}

    // --> Adding this on ListFragment.kt <--

    //viewModel.loadPage.observe(viewLifecycleOwner, Observer { loadPage ->
    //     if (loadPage) {
    //         scrollRecyclerView()
    //   }
    //})

    // Add this method to ListFragment.kt

    //private var page: Int = 1

    //private var lastPos: Int = 0

    //private fun scrollRecyclerView() {
    //        scrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
    //            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
    //                super.onScrollStateChanged(recyclerView!!, newState)
    //                val itemcount = recyclerView!!.layoutManager!!.itemCount
    //                lastPos = (binding.recyclerViewList.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
    //                if (itemcount == lastPos + 1) {
    //                    page += 1
    //                    viewModel.updatePage(page)
    //                    binding.recyclerViewList.removeOnScrollListener(scrollListener)
    //                }
    //            }
    //        }
    //        binding.recyclerViewList.addOnScrollListener(scrollListener)
    //    }

}