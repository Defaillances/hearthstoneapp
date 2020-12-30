package com.example.hearthstoneapp.controller

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.example.hearthstoneapp.model.Card
import com.example.hearthstoneapp.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class MainController(mainActivity: MainActivity) {
    private val activity: MainActivity
    private val sharedPreferences: SharedPreferences
    fun onStart() {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Builder()
            .baseUrl("https://api.hearthstonejson.com/v1/28855/enUS/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val restCardApi: RestCardApi = retrofit.create(RestCardApi::class.java)
        if (!sharedPreferences.contains(Constants.DATABASE_NAME)) {
            val call: Call<List<Card>> = restCardApi.getListCard()
            call.enqueue(object : Callback<List<Card?>?>() {
                fun onResponse(call: Call<List<Card?>?>?, response: Response<List<Card?>?>) {
                    val listCard: List<Card> = response.body()
                    //regarder sur chaque élément ->favoris
                    storeData(listCard)
                }

                fun onFailure(call: Call<List<Card?>?>?, t: Throwable?) {
                    Log.d("ERROR", "Api ERROR")
                }
            })
        } else {
            val json = sharedPreferences.getString(Constants.DATABASE_NAME, "")
            val listType: Type = object : TypeToken<List<Card?>?>() {}.getType()
        }
    }

    private fun storeData(listCard: List<Card>) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(listCard)
        editor.putString(Constants.DATABASE_NAME, json)
        editor.apply()
    }

    init {
        activity = mainActivity
        sharedPreferences =
            activity.getSharedPreferences(Constants.DATABASE_NAME, Context.MODE_PRIVATE)
    }
}