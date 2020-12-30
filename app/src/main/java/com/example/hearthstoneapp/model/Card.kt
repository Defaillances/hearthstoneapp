package com.example.hearthstoneapp.model

class Card {
    val cardClass: String? = null
        get() = if (field === "NEUTRAL") {
            "ALL"
        } else {
            field
        }
    val isCollectible: String = false
        get() {
            return if (field) {
                "This card is currently craftable"
            } else {
                "This card is currently not craftable"
            }
        }
    val cost = 0
    val name: String? = null
    val rarity: String? = null
    val set: String? = null
    private var text: String? = null
    val type: String? = null
    val id: String? = null
    var isFav = false
        private set

    fun getText(): String? {
        if (text != null) {
            text = text!!.replace("<b>", "")
            text = text!!.replace("</b>", " ")
            text = text!!.replace("#", "")
            text = text!!.replace("<i>", "")
            text = text!!.replace("</i>", "")
            text = text!!.replace("[x]", "")
            text = text!!.replace("$", "")
        }
        return text
    }

    fun changeFav() {
        isFav = !isFav
    }
}