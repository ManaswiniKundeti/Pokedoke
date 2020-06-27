package com.manu.pokedoke.repository

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.manu.pokedoke.model.Pokemon
import com.manu.pokedoke.model.PokemonInfo
import com.manu.pokedoke.model.PokemonResponse
import com.manu.pokedoke.network.IPokemonService
import com.manu.pokedoke.persistence.PokemonDao
import com.manu.pokedoke.viewstate.Error
import com.manu.pokedoke.viewstate.Loading
import com.manu.pokedoke.viewstate.Success
import com.manu.pokedoke.viewstate.ViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainRepository(
    private val pokemonService: IPokemonService,
    private val pokemonDao : PokemonDao): IMainRepository {

    private val TAG = MainRepository::class.java.simpleName

    /** LIVE DATA **/
    private val _pokemonListLiveData : MutableLiveData<ViewState<List<Pokemon>>> = MutableLiveData()
    val pokemonListLiveData :LiveData<ViewState<List<Pokemon>>> = _pokemonListLiveData

    override fun getPokemonList() {
        FetchPokemonListTask(_pokemonListLiveData, pokemonService, pokemonDao).execute()
    }

    class FetchPokemonListTask(private val pokemonLiveData: MutableLiveData<ViewState<List<Pokemon>>>,
        private val pokemonService: IPokemonService,
        private val pokemonDao: PokemonDao): AsyncTask<Void, Void, List<Pokemon>>() {

        private val TAG = FetchPokemonListTask::class.java.simpleName

        override fun onPreExecute() {
            super.onPreExecute()
            pokemonLiveData.value = Loading
        }

        override fun doInBackground(vararg p0: Void?): List<Pokemon>? {
            return try {
                val response = pokemonService.fetchPokemonList().execute()
                if (response.isSuccessful && response.body() != null) {
                    val pokemons = response.body()!!.results
                    pokemonDao.insertPokemons(pokemons)
                    pokemonDao.getPokemons()
                } else {
                    null
                }
            } catch (e: Exception) {
                Log.e(TAG, e.message)
                null
            }
        }

        override fun onPostExecute(result: List<Pokemon>?) {
            super.onPostExecute(result)
            if (result == null) {
                pokemonLiveData.value = Error("Error fetching list of pokemon")
            } else {
                pokemonLiveData.value = Success(result)
            }
        }
    }

}