package com.manu.pokedoke.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.manu.pokedoke.model.PokemonInfo
import com.manu.pokedoke.repository.DetailRepository
import com.manu.pokedoke.repository.MainRepository



class DetailActivityViewModel(private val detailRepository: DetailRepository) : ViewModel() {

    val pokemonInfoData : LiveData<PokemonInfo> = detailRepository.pokemonDetailsLiveData
//
//    init {
//       // detailRepository.getPokemonDetails(name)
//    }

    fun fetchPokemonDetails(name: String) {
        detailRepository.getPokemonDetails(name)
    }

}