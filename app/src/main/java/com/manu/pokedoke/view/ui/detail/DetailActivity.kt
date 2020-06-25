package com.manu.pokedoke.view.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import coil.api.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.manu.pokedoke.R
import com.manu.pokedoke.model.PokemonInfo
import com.manu.pokedoke.viewmodels.DetailActivityViewModel
import com.manu.pokedoke.viewmodels.DetailActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {
    private val viewmodelFactory by lazy { DetailActivityViewModelFactory(this) }
    private val viewModel: DetailActivityViewModel by viewModels {
        viewmodelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent: Intent = intent
        val nameFromMainActivity = intent.getStringExtra("pokemon_name") ?: "pikachu"
        val imageUrl = intent.getStringExtra("pokemon_image_url")

        //val imageUrl = findViewById<ImageView>(R.id.pokemon_image)
        viewModel.pokemonInfoData.observe(this, Observer<PokemonInfo> { pokemonInfo ->
            pokemon_image.load(imageUrl)
            pokemon_name.text = pokemonInfo.name
            if(pokemonInfo.types.size == 1){
                type_name_one.text = pokemonInfo.types[0].type.name
                type_name_two.isVisible = false
            }else{
                type_name_one.text = pokemonInfo.types[0].type.name
                type_name_two.text = pokemonInfo.types[1].type.name
            }
            height.text = pokemonInfo.getHeightString()
            weight.text = pokemonInfo.getWeightString()
//            weight.text =  pokemonInfo.hp.toString()

            progress_hp.labelText = pokemonInfo.getHpString()
            progress_hp.max = PokemonInfo.maxHp.toFloat()
            progress_hp.progress = pokemonInfo.getHp().toFloat()

            progress_attack.labelText = pokemonInfo.getAttackString()
            progress_attack.max = PokemonInfo.maxAttack.toFloat()
            progress_attack.progress = pokemonInfo.getAttack().toFloat()

            progress_defense.labelText = pokemonInfo.getDefenseString()
            progress_defense.max = PokemonInfo.maxDefense.toFloat()
            progress_defense.progress = pokemonInfo.getDefense().toFloat()

            progress_speed.labelText = pokemonInfo.getSpeedString()
            progress_speed.max = PokemonInfo.maxSpeed.toFloat()
            progress_speed.progress = pokemonInfo.getSpeed().toFloat()

            progress_exp.labelText = pokemonInfo.getExpString()
            progress_exp.max = PokemonInfo.maxExp.toFloat()
            progress_exp.progress = pokemonInfo.getExp().toFloat()

        })

        viewModel.fetchPokemonDetails(nameFromMainActivity)
    }
}