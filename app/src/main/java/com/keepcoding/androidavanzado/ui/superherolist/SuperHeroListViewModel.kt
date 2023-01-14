package com.keepcoding.androidavanzado.ui.superherolist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.domain.SuperHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SuperHeroListViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _heros = MutableLiveData<List<SuperHero>>()
    val heros: LiveData<List<SuperHero>> get() = _heros

    private val _state = MutableLiveData<SuperHeroListState>()
    val state: LiveData<SuperHeroListState> get() = _state

    companion object{
        private const val TAG = "ListViewModel"
    }

    fun getHeros(){
        viewModelScope.launch {
            val superheros = withContext(Dispatchers.IO){
                repository.getHerosWithCache()
            }
            _heros.value = superheros
            Log.d(TAG, superheros.toString())
        }
    }

    fun getHerosWithException(){
        viewModelScope.launch {
            val superHeroListState = withContext(Dispatchers.IO){
                repository.getHerosWithException()
            }
            _state.value = superHeroListState
        }
    }
}