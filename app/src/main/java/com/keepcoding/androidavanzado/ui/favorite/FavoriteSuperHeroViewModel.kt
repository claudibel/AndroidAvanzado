package com.keepcoding.androidavanzado.ui.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.domain.SuperHero
import com.keepcoding.androidavanzado.ui.superherolist.SuperHeroListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteSuperHeroViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _favorites = MutableLiveData<List<SuperHero>>()
    val favorites: LiveData<List<SuperHero>> get() = _favorites

    private val _state = MutableLiveData<SuperHeroListState>()
    val state: LiveData<SuperHeroListState> get() = _state

    companion object{
        private const val TAG = "ListViewModel"
    }

    fun getFavoriteList(){
        viewModelScope.launch {
            val superheros = withContext(Dispatchers.IO){
                repository.getFavoritesList()
            }
            _favorites.value = superheros
        }
    }

}