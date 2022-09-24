package com.android.rickmorty.detail_screen.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.data.models.RickMorty
import com.android.rickmorty.commons.BaseViewModel

class ViewModelShared : BaseViewModel() {
    private var _rickmorty = MutableLiveData<RickMorty>()

    val rickmorty: LiveData<RickMorty>
        get() = _rickmorty

    fun setCharacter(rickMorty: RickMorty) {
        _rickmorty.value = rickMorty
    }
}