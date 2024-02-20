package com.ruzgaruludag.rickandmorty.newtask.activities.home

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ruzgaruludag.rickandmorty.R
import com.ruzgaruludag.rickandmorty.databinding.ActivityHomeBinding
import com.ruzgaruludag.rickandmorty.databinding.ListItemBinding
import com.ruzgaruludag.rickandmorty.newtask.activities.detail.CharacterDetailActivity
import com.ruzgaruludag.rickandmorty.newtask.adapters.CharactersItemClickListener
import com.ruzgaruludag.rickandmorty.newtask.base.BaseActivity
import com.ruzgaruludag.rickandmorty.newtask.models.Character
import com.ruzgaruludag.rickandmorty.newtask.utils.Constant
import com.ruzgaruludag.rickandmorty.newtask.viewmodels.CharactersViewModel

class HomeActivity:BaseActivity(),CharactersItemClickListener {

    // TODO: 20.02.2024 properties
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel : CharactersViewModel


    // TODO: 20.02.2024 lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = CharactersViewModel(apiInterface,this)
        binding.viewModel = viewModel


        startIndicator(R.color.black)
        viewModel.getCharactersList{response ->
            stopIndicator()
            Log.e(ContentValues.TAG,response.body()?.results!!.joinToString())

            viewModel.charactersList.clear()
            viewModel.charactersList.addAll(response.body()!!.results)

        }


    }

    override fun onClickCharacter(binding: ListItemBinding, item: Character) {
        Constant.VariableHolder.selectCharacterModel = item
        startIndicator(R.color.black)
        val intent = Intent(this,CharacterDetailActivity::class.java)
        stopIndicator()
        startActivity(intent)
    }


    // TODO: 20.02.2024 functions
}