package com.example.hearthstoneapp.view

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.hearthstoneapp.R
import com.squareup.picasso.Picasso
import xavier.albanet.projetprogrammationmobile.R

class CardActivity : Activity() {
    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        incomingIntent
    }

    private val incomingIntent: Unit
        private get() {
            if (intent.hasExtra("Cardname") && intent.hasExtra("Cardclass") && intent.hasExtra("Cardcost") && intent.hasExtra(
                    "Cardrarity"
                ) && intent.hasExtra("Cardtype") && intent.hasExtra("Cardset") && intent.hasExtra("Cardtext") && intent.hasExtra(
                    "Cardid"
                )
            ) {
                val cardName = intent.getStringExtra("Cardname")
                val cardClass = intent.getStringExtra("Cardclass")
                val cardCost = intent.getIntExtra("Cardcost", 0)
                val cardRarity = intent.getStringExtra("Cardrarity")
                val cardType = intent.getStringExtra("Cardtype")
                val cardSet = intent.getStringExtra("Cardset")
                val cardCollectible = intent.getStringExtra("Cardcollectible")
                val cardText = intent.getStringExtra("Cardtext")
                val cardId = intent.getStringExtra("Cardid")
                setCard(
                    cardName,
                    cardClass,
                    cardCost,
                    cardRarity,
                    cardType,
                    cardSet,
                    cardCollectible,
                    cardText,
                    cardId
                )
            }
        }

    private fun setCard(
        cardName: String,
        cardClass: String,
        cardCost: Int,
        cardRarity: String,
        cardType: String,
        cardSet: String,
        cardCollectible: String,
        cardText: String,
        cardId: String
    ) {
        val name = findViewById<TextView>(R.id.CardName)
        name.text = cardName
        val Class = findViewById<TextView>(R.id.CardClass)
        Class.text = "It's for $cardClass"
        val cost = findViewById<TextView>(R.id.CardCost)
        cost.text = "It costs $cardCost mana"
        val rarity = findViewById<TextView>(R.id.CardRarity)
        rarity.text = "It's a $cardRarity card"
        val type = findViewById<TextView>(R.id.CardType)
        type.text = "It's a $cardType"
        val set = findViewById<TextView>(R.id.CardSet)
        set.text = "Fom the set $cardSet"
        val collectible = findViewById<TextView>(R.id.CardCollectible)
        collectible.text = cardCollectible
        val text = findViewById<TextView>(R.id.CardText)
        text.text = cardText
        val id = findViewById<ImageView>(R.id.CardId)
        Picasso.get().load("https://art.hearthstonejson.com/v1/256x/$cardId.jpg").into(id)
    }
}