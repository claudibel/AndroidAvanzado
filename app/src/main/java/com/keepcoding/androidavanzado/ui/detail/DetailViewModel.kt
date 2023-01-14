package com.keepcoding.androidavanzado.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.data.local.model.SuperHeroLocal
import com.keepcoding.androidavanzado.domain.SuperHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

//    private val _hero = MutableLiveData<List<SuperHero>>()
//    val hero : LiveData<List<SuperHero>>
//    get() = _hero

    private val _state = MutableLiveData<DetailState>()
    val state : LiveData<DetailState>
    get() = _state

    private val _isFavoriteHero = MutableLiveData<Boolean>()
    val isFavoriteHero : LiveData<Boolean>
        get() = _isFavoriteHero

    private val _location = MutableLiveData<MutableList<Double>>()
    val location : LiveData<MutableList<Double>>
    get() = _location

    companion object{
        private const val TAG = "DetailViewModel"
        private var locationsVisited : MutableList<Double> = mutableListOf()
    }

    fun getHeroLocations(heroId : String){
        viewModelScope.launch {
            val locations = async(Dispatchers.IO){
                repository.getHeroLocations(heroId)
            }
            for(numberOfLocations in locations.await().indices){
                locationsVisited.add(locations.await()[numberOfLocations].latitud.toDouble())
                locationsVisited.add(locations.await()[numberOfLocations].longitud.toDouble())
            }
            _location.value = locationsVisited
            locationsVisited = mutableListOf()
        }
    }

    fun favorite(heroId: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.favorite(heroId)
            }
        }
    }

    fun isFavorite(heroId: String){

        viewModelScope.launch {
            val isFavorite = withContext(Dispatchers.IO){
                repository.isFavorite(heroId)
            }
            _isFavoriteHero.value = isFavorite
        }
    }


    fun getHeroDetail(name : String){
        viewModelScope.launch {
            val detailState = withContext(Dispatchers.IO){
                repository.getHeroDetail(name)
            }
            _state.value = detailState
        }
    }
}