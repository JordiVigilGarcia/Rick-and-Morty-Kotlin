package com.android.rickmorty.profile_screen.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.data.repository.RickMortyRepository
import com.android.rickmorty.commons.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelProfile(private val repository: RickMortyRepository): BaseViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _name.postValue(repository.getName())
            _username.postValue(repository.getUsername())
            _description.postValue(repository.getDescription())
        }
    }

    fun setName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setName(name)
        }
    }

    fun setUsername(surname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setUsername(surname)
        }
    }

    fun setDescription(description: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.setDescription(description)
        }
    }
}