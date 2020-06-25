package com.manu.pokedoke.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.manu.pokedoke.model.PokemonInfo
import com.manu.pokedoke.network.IPokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRepository(private val pokemonService : IPokemonService) :IDetailRepository {

    private val TAG = DetailRepository::class.java.simpleName

    /** LIVE DATA **/
    private val _pokemonDetailsLiveData : MutableLiveData<PokemonInfo> = MutableLiveData()
    val pokemonDetailsLiveData : LiveData<PokemonInfo> = _pokemonDetailsLiveData


    override fun getPokemonDetails(name: String) {
        pokemonService.fetchPokemonDetails(name).enqueue(object : Callback<PokemonInfo> {
            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected exception
             * occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<PokemonInfo>, t: Throwable) {
                Log.e(TAG,"Error fetching pokemon details", t)
            }

            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(call: Call<PokemonInfo>, response: Response<PokemonInfo>) {
                val pokemonDetails = response.body()
                _pokemonDetailsLiveData.postValue(pokemonDetails)
            }

        })
    }

}