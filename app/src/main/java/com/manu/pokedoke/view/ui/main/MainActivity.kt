package com.manu.pokedoke.view.ui.main

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manu.pokedoke.R
import com.manu.pokedoke.extensions.changeColor
import com.manu.pokedoke.model.Pokemon
import com.manu.pokedoke.view.adapter.PokemonListAdapter
import com.manu.pokedoke.viewmodels.MainActivityViewModel
import com.manu.pokedoke.viewmodels.MainActivityViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewmodelFactory by lazy { MainActivityViewModelFactory(this) }
    private val viewModel: MainActivityViewModel by viewModels {
        viewmodelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemonList: RecyclerView = findViewById(R.id.pokemon_list)
        pokemonList.layoutManager = GridLayoutManager(this, 2)

        val pokemonListAdapter = PokemonListAdapter(this)
        pokemonList.adapter = pokemonListAdapter

        //Create an observer which updates UI in after network calls
        viewModel.pokemonLiveData.observe(this, Observer<List<Pokemon>> {pokemonData ->
            pokemonListAdapter.setPokemonList(pokemonData)
        })

        changeColor(getColor(R.color.colorPrimary))
    }
}