package com.manu.pokedoke.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.manu.pokedoke.R
import com.manu.pokedoke.model.Pokemon
import com.manu.pokedoke.view.ui.detail.DetailActivity
import com.manu.pokedoke.view.ui.main.MainActivity
import kotlinx.android.synthetic.main.item_pokemon_list.view.*


class PokemonListItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    fun bindView(pokemonModel : Pokemon){
        itemView.pokemon_name_text_view.text = pokemonModel.name
        Glide.with(itemView.context)
            .load(pokemonModel.getImageUrl())
            .listener(
                GlidePalette.with(pokemonModel.getImageUrl())
                .use(BitmapPalette.Profile.MUTED_LIGHT)
                .intoCallBack { palette ->
                    val rgb = palette?.dominantSwatch?.rgb
                    if (rgb != null) {
                        itemView.item_pokemon_card_view.setCardBackgroundColor(rgb)
                    }
                }
                .crossfade(true))
            .into(itemView.pokemon_image_view)
    }
}

class PokemonListAdapter(private val mainActivity: MainActivity) : RecyclerView.Adapter<PokemonListItemViewHolder>(){
    private var listOfPokemons = listOf<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_list, parent, false)
        return PokemonListItemViewHolder(itemView)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return listOfPokemons.size
    }

    override fun onBindViewHolder(holder: PokemonListItemViewHolder, position: Int) {
        val pokemonViewHolder = holder
        pokemonViewHolder.bindView(listOfPokemons[position])
        pokemonViewHolder.itemView.setOnClickListener {
            val intent = Intent(mainActivity, DetailActivity::class.java)
            intent.putExtra("pokemon_name", listOfPokemons[position].name)
            intent.putExtra("pokemon_image_url", listOfPokemons[position].getImageUrl())
            mainActivity.startActivity(intent)
        }
    }

    fun setPokemonList(listOfPokemons : List<Pokemon>){
        this.listOfPokemons = listOfPokemons
        notifyDataSetChanged()
    }
}