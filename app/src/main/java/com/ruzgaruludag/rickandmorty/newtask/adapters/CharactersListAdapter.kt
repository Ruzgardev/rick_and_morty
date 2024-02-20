package com.ruzgaruludag.rickandmorty.newtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.ruzgaruludag.rickandmorty.R
import com.ruzgaruludag.rickandmorty.databinding.ListItemBinding
import com.ruzgaruludag.rickandmorty.newtask.models.Character
import com.squareup.picasso.Picasso

class CharactersListAdapter(
    private val character: ObservableArrayList<Character>,
    val listener: CharactersItemClickListener
):RecyclerView.Adapter<CharactersListAdapter.CharacterListViewHolder>(){


    override fun getItemCount() = character.count()

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val item = character[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val bind: ListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item,parent,false
        )
        return CharacterListViewHolder(bind,listener)
    }

    class CharacterListViewHolder(
        private val binding: ListItemBinding,
        val listener: CharactersItemClickListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Character) {
            binding.episodeId.text = item.id.toString()
            binding.episodeName.text = item.name
            binding.episodeAirDate.text = item.species
            binding.container.setOnClickListener { listener.onClickCharacter(binding,item) }
            Picasso.get().load(item.image).into(binding.episodeImage)
            binding.executePendingBindings()
        }
    }
}



interface CharactersItemClickListener{
    fun onClickCharacter(binding: ListItemBinding,item: Character)
}