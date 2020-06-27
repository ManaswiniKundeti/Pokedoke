package com.manu.pokedoke.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.manu.pokedoke.model.PokemonInfo
import com.manu.pokedoke.repository.DetailRepository
import com.manu.pokedoke.repository.MainRepository
import com.manu.pokedoke.viewstate.ViewState


class DetailActivityViewModel(private val detailRepository: DetailRepository) : ViewModel() {

    val pokemonInfoData : LiveData<ViewState<PokemonInfo>> = detailRepository.pokemonDetailsLiveData


    fun fetchPokemonDetails(name: String) {
        detailRepository.getPokemonDetails(name)
    }

}