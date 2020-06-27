package com.manu.pokedoke.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manu.pokedoke.model.Pokemon
import com.manu.pokedoke.repository.MainRepository
import com.manu.pokedoke.viewstate.ViewState

class MainActivityViewModel(mainRepository : MainRepository) : ViewModel() {

    val pokemonLiveData : LiveData<ViewState<List<Pokemon>>> = mainRepository.pokemonListLiveData

    init {
        mainRepository.getPokemonList()
    }
}