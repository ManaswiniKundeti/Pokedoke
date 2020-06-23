package com.manu.pokedoke.view.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.manu.pokedoke.R
import com.manu.pokedoke.model.Pokemon
import com.manu.pokedoke.model.PokemonResponse
import com.manu.pokedoke.view.adapter.PokemonListAdapter
import com.manu.pokedoke.viewmodels.MainActivityViewModel
import com.manu.pokedoke.viewmodels.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.item_pokemon_list.*

class MainActivity : AppCompatActivity() {

    private val viewmodelFactory by lazy { MainActivityViewModelFactory() }
    private val viewModel: MainActivityViewModel by viewModels {
        viewmodelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemonList: RecyclerView = findViewById(R.id.pokemon_list)
        pokemonList.layoutManager = GridLayoutManager(this, 2)

        val pokemonListAdapter = PokemonListAdapter()
        pokemonList.adapter = pokemonListAdapter

        //pokemonListAdapter.setPokemonList(generateDummyData())

        //Create an observer which updates UI in after network calls
        viewModel.pokemonLiveData.observe(this, Observer<List<Pokemon>> {pokemonData ->
            pokemon_name_text_view.text = pokemonData[0]?.name
            pokemon_image_view.load(pokemonData[0]?.getImageUrl())
        })
    }


//    private fun generateDummyData(): List<Pokemon> {
//            val listOfPokemon = mutableListOf<Pokemon>()
//
//            var pokemonModel = Pokemon(0,"manu","https://pbs.twimg.com/profile_images/1212398377553412096/HaWAuh90_400x400.jpg")
//            listOfPokemon.add(pokemonModel)
//
//            pokemonModel = Pokemon(0,"molu","https://cdn.mos.cms.futurecdn.net/4qNfmM5fuedTF6peFDNi3g.jpg")
//            listOfPokemon.add(pokemonModel)
//
//            return listOfPokemon
//    }
}