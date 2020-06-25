package com.manu.pokedoke.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlin.random.Random


@Entity
data class PokemonInfo(
    @field:Json(name = "id") @PrimaryKey val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "weight") val weight: Int,
    @field:Json(name = "base_experience") val experience: Int,
    @field:Json(name = "types") val types: List<TypeResponse>
) {
    private var hp: Int? = null
    private var attack: Int? = null
    private var defense: Int? = null
    private var speed: Int? = null
    private var exp: Int? = null

    fun getHp(): Int {
        hp = Random.nextInt(0, maxHp)
        return hp!!
    }

    fun getAttack(): Int {
        attack = Random.nextInt(0,maxAttack)
        return attack!!
    }

    fun getDefense(): Int {
        defense = Random.nextInt(0, maxDefense)
        return defense!!
    }

    fun getSpeed() : Int{
        speed = Random.nextInt(0,maxSpeed)
        return speed!!
    }

    fun getExp() : Int{
        exp = Random.nextInt(0, maxExp)
        return exp!!
    }

    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = "${getHp()}/$maxHp"
    fun getAttackString(): String = "${getAttack()}/$maxAttack"
    fun getDefenseString(): String = "${getDefense()}/$maxDefense"
    fun getSpeedString(): String = "${getSpeed()}/$maxSpeed"
    fun getExpString(): String = "${getExp()}/$maxExp"

    data class TypeResponse(
        @field:Json(name = "slot") val slot: Int,
        @field:Json(name = "type") val type: Type
    )

    data class Type(
        @field:Json(name = "name") val name: String
    )

    companion object {
        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxExp = 1000
    }
}