package com.ruzgaruludag.rickandmorty.newtask.activities.detail

import android.os.Bundle
import android.util.Log
import com.ruzgaruludag.rickandmorty.databinding.ActivityCharacterDetailBinding
import com.ruzgaruludag.rickandmorty.newtask.base.BaseActivity
import com.ruzgaruludag.rickandmorty.newtask.models.Character
import com.ruzgaruludag.rickandmorty.newtask.utils.Constant
import com.squareup.picasso.Picasso

class CharacterDetailActivity:BaseActivity() {

    // TODO: 20.02.2024 properties
    private lateinit var binding: ActivityCharacterDetailBinding
    val characterDetail:Character = Constant.VariableHolder.selectCharacterModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = Constant.VariableHolder.selectCharacterModel

        Log.e("deneme detail page", data.toString())

        Picasso.get().load(characterDetail.image).into(binding.characterImage)
        binding.characterName.text = characterDetail.name
        binding.characterSpecies.text = characterDetail.species
    }
}