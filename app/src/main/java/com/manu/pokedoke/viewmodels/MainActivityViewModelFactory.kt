package com.manu.pokedoke.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manu.pokedoke.network.createPokemonService
import com.manu.pokedoke.persistence.AppDatabase
import com.manu.pokedoke.repository.MainRepository
import java.lang.IllegalArgumentException

class MainActivityViewModelFactory(private val context: Context) :ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(MainRepository(createPokemonService(), AppDatabase.getAppDatabase(context)!!.pokemonDao())) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }

}