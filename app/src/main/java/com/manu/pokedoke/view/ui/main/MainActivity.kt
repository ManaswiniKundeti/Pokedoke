package com.manu.pokedoke.view.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manu.pokedoke.R
import com.manu.pokedoke.extensions.changeColor
import com.manu.pokedoke.extensions.hide
import com.manu.pokedoke.extensions.show
import com.manu.pokedoke.model.Pokemon
import com.manu.pokedoke.view.adapter.PokemonListAdapter
import com.manu.pokedoke.viewmodels.MainActivityViewModel
import com.manu.pokedoke.viewmodels.MainActivityViewModelFactory
import com.manu.pokedoke.viewstate.Error
import com.manu.pokedoke.viewstate.Loading
import com.manu.pokedoke.viewstate.Success
import com.manu.pokedoke.viewstate.ViewState
import kotlinx.android.synthetic.main.activity_main.*

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
        viewModel.pokemonLiveData.observe(this, Observer<ViewState<List<Pokemon>>> { viewState ->
            when (viewState) {
                is Success -> {
                    main_progress_bar.hide()
                    pokemonListAdapter.setPokemonList(viewState.data)
                }
                is Error -> {
                    main_progress_bar.hide()
                    Toast.makeText(this, viewState.errMsg, Toast.LENGTH_SHORT).show()
                }
                is Loading -> {
                    main_progress_bar.show()
                }
            }

        })

        changeColor(getColor(R.color.colorPrimary))
    }
}