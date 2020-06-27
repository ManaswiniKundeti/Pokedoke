package com.manu.pokedoke.repository

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.manu.pokedoke.model.PokemonInfo
import com.manu.pokedoke.network.IPokemonService
import com.manu.pokedoke.viewstate.Error
import com.manu.pokedoke.viewstate.Loading
import com.manu.pokedoke.viewstate.Success
import com.manu.pokedoke.viewstate.ViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRepository(private val pokemonService : IPokemonService) :IDetailRepository {

    private val TAG = DetailRepository::class.java.simpleName

    /** LIVE DATA **/
    private val _pokemonDetailsLiveData : MutableLiveData<ViewState<PokemonInfo>> = MutableLiveData()
    val pokemonDetailsLiveData : LiveData<ViewState<PokemonInfo>> = _pokemonDetailsLiveData


    override fun getPokemonDetails(name: String) {
        FetchPokemonDetailsTask(_pokemonDetailsLiveData,pokemonService).execute(name)
    }

    class FetchPokemonDetailsTask( private val _pokemonDetailsLiveData : MutableLiveData<ViewState<PokemonInfo>>,
                                   private val pokemonService : IPokemonService) :
        AsyncTask<String, Void, PokemonInfo>() {

        private val TAG = FetchPokemonDetailsTask::class.java.simpleName

        override fun onPreExecute() {
            super.onPreExecute()
            _pokemonDetailsLiveData.value = Loading
        }

        override fun doInBackground(vararg p0: String): PokemonInfo? {
            return try{
                val response =  pokemonService.fetchPokemonDetails(p0[0]).execute()
                if(response.isSuccessful) {
                    response.body()
                }else{
                    null
                }
            } catch (e: Exception){
                Log.e(TAG, e.message)
                null
            }
        }

        override fun onPostExecute(result: PokemonInfo?) {
            super.onPostExecute(result)
            if(result == null){
                _pokemonDetailsLiveData.value = Error("Error loading pokemon details")
            }else{
                _pokemonDetailsLiveData.value = Success(result)
            }
        }
    }

}