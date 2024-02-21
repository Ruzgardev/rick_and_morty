package com.ruzgaruludag.rickandmorty.newtask.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ruzgaruludag.rickandmorty.BR
import com.ruzgaruludag.rickandmorty.newtask.adapters.CharactersItemClickListener
import com.ruzgaruludag.rickandmorty.newtask.adapters.CharactersListAdapter
import com.ruzgaruludag.rickandmorty.newtask.models.Character
import com.ruzgaruludag.rickandmorty.newtask.models.CharacterBaseModel
import com.ruzgaruludag.rickandmorty.newtask.networking.NetworkManager
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel(
    private val apiManager: NetworkManager,
    val listener : CharactersItemClickListener
) : BaseObservable() {

    var charactersList: ObservableArrayList<Character> = ObservableArrayList()
        set(charactersList) {
            field = charactersList
            notifyPropertyChanged(BR.charactersList)
        }
        @Bindable get

    fun getCharactersList(
        resultCallback: ((Response<CharacterBaseModel>) -> Unit)? = null
    ) = CoroutineScope(Dispatchers.Main).launch(CoroutineExceptionHandler { coroutineContext, error ->
        Log.e(ContentValues.TAG, error.message.toString())
    }) {
        val result = apiManager.gelAllCharacters()
        resultCallback?.invoke(result)

        /*
        result.body()?.results?.let { characters ->
            charactersList.addAll(characters)
        }
         */
    }


    companion object {
        @JvmStatic
        @BindingAdapter(
            "setCharactersData",
            "setCharactersListener"
        )
        fun setCharactersData(
            recyclerView: RecyclerView,
            customerOperations: ObservableArrayList<Character>,
            listener: CharactersItemClickListener
        ) {
            recyclerView.adapter = CharactersListAdapter(customerOperations, listener)
            recyclerView.layoutManager =
                GridLayoutManager(recyclerView.context,2)

            recyclerView.adapter?.notifyDataSetChanged()
        }

    }
}