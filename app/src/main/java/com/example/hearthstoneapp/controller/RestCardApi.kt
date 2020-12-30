package com.example.hearthstoneapp.controller

import android.telecom.Call
import com.example.hearthstoneapp.model.Card
import retrofit2.Call
import retrofit2.http.GET

interface RestCardApi {
    @get:GET("cards.collectible.json")
    val listCard: Call<List<Card?>?>?
}