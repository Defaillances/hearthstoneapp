package com.example.hearthstoneapp.controller

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.example.hearthstoneapp.model.Card
import com.example.hearthstoneapp.view.FavoritesFragment
import java.lang.reflect.Type

class FavoriteController(favoriteFragment: FavoritesFragment) {
    private val fragment: FavoritesFragment = favoriteFragment
    private val sharedPreferences: SharedPreferences
    private var listCard: List<Card>? = null
    fun onStart() {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        val json = sharedPreferences.getString(Constants.DATABASE_NAME, "")
        val listType: Type = object : TypeToken<List<Card?>?>() {}.getType()
        listCard = gson.fromJson(json, listType)
        fragment.showListFav(listCard)
    }

    private fun storeData(listCard: List<Card>?) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(listCard)
        editor.putString(Constants.DATABASE_NAME, json)
        editor.apply()
    }

    fun onClickedFavorite(cardClicked: Card) {
        for (card in listCard!!) {
            if (card.getId().equals(cardClicked.getId())) {
                card.changeFav()
                break
            }
        }
        fragment.showListFav(listCard!!)
        storeData(listCard)
    }

    init {
        sharedPreferences = fragment.context
            ?.getSharedPreferences(Constants.DATABASE_NAME, Context.MODE_PRIVATE) ?:
    }
}