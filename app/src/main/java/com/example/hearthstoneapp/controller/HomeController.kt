package com.example.hearthstoneapp.controller

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.example.hearthstoneapp.model.Card
import com.example.hearthstoneapp.view.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xavier.albanet.projetprogrammationmobile.R
import java.lang.reflect.Type

class HomeController(homeFragment: HomeFragment) {
    private val fragment: HomeFragment
    private val sharedPreferences: SharedPreferences
    private var listCard: List<Card>? = null
    fun onStart() {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        val json = sharedPreferences.getString(Constants.DATABASE_NAME, "")
        val listType: Type = object : TypeToken<List<Card?>?>() {}.getType()
        listCard = gson.fromJson(json, listType)
        fragment.showList(listCard)
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
        storeData(listCard)
    }

    init {
        fragment = homeFragment
        sharedPreferences = fragment.getContext()
            .getSharedPreferences(Constants.DATABASE_NAME, Context.MODE_PRIVATE)
    }
}