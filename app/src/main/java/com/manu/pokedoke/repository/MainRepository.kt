package com.manu.pokedoke.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.manu.pokedoke.model.Pokemon
import com.manu.pokedoke.model.PokemonResponse
import com.manu.pokedoke.network.IPokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(private val pokemonService: IPokemonService): IRepository {

    private val TAG = MainRepository::class.java.simpleName

    /** LIVE DATA **/
    private val _pokemonListLiveData : MutableLiveData<List<Pokemon>> = MutableLiveData()
    val pokemonListLiveData : LiveData<List<Pokemon>> = _pokemonListLiveData

    override fun getPokemonList() {
        pokemonService.fetchPokemonList().enqueue(object : Callback<PokemonResponse>{
            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected exception
             * occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Log.e(TAG,"Error fetching pokemon list", t)
            }

            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                _pokemonListLiveData.postValue(response.body()?.results)
            }

        })
    }

}