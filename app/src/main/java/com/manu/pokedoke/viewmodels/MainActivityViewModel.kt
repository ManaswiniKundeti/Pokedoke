package com.manu.pokedoke.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.manu.pokedoke.model.Pokemon
import com.manu.pokedoke.repository.MainRepository

class MainActivityViewModel(mainRepository : MainRepository) : ViewModel() {

    val pokemonLiveData : LiveData<List<Pokemon>> = mainRepository.pokemonListLiveData

    init {
        mainRepository.getPokemonList()
    }
}