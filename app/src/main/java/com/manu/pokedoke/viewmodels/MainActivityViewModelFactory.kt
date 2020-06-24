package com.manu.pokedoke.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manu.pokedoke.network.createPokemonService
import com.manu.pokedoke.persistence.AppDatabase
import com.manu.pokedoke.repository.MainRepository
import java.lang.IllegalArgumentException

class MainActivityViewModelFactory(private val context: Context) :ViewModelProvider.Factory {
    /**
     * Creates a new instance of the given `Class`.
     *
     *
     *
     * @param modelClass a `Class` whose instance is requested
     * @param <T>        The type parameter for the ViewModel.
     * @return a newly created ViewModel
    </T> */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(MainRepository(createPokemonService(), AppDatabase.getAppDatabase(context)!!.pokemonDao())) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }

}