package com.example.hearthstoneapp.controller

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.hearthstoneapp.model.Card
import com.example.hearthstoneapp.view.CardActivity
import xavier.albanet.projetprogrammationmobile.R
import java.util.*

class MyAdapter(context: Context, myDataset: List<Card>, listener: OnItemClickListener) :
    RecyclerView.Adapter<MyAdapter.ViewHolder?>(),
    Filterable {
    private val values: List<Card>
    private var valuesFiltered: List<Card>
    private val myContext: Context
    private val listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(card: Card?)
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one com.example.hearthstoneapp.view per item, and
    // you provide access to all the views for a data item in a com.example.hearthstoneapp.view holder
    inner class ViewHolder(var layout: View) : RecyclerView.ViewHolder(
        layout
    ) {
        // each data item is just a string in this case
        var txtHeader: TextView
        var txtFooter: TextView
        var imgIcon: ImageView
        var imgFavorite: ImageView

        init {
            txtHeader = layout.findViewById<View>(R.id.firstLine) as TextView
            txtFooter = layout.findViewById<View>(R.id.secondLine) as TextView
            imgIcon = layout.findViewById<View>(R.id.icon) as ImageView
            imgFavorite = layout.findViewById(R.id.image_favorite)
        }
    }

    // Create new views (invoked by the layout manager)
    fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        // create a new com.example.hearthstoneapp.view
        val inflater = LayoutInflater.from(
            parent.context
        )
        val v: View = inflater.inflate(R.layout.row_layout, parent, false)
        // set the com.example.hearthstoneapp.view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    // Replace the contents of a com.example.hearthstoneapp.view (invoked by the layout manager)
    fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the com.example.hearthstoneapp.view with that element
        val currentCard: Card = valuesFiltered[position]
        val name: String = currentCard.getName()
        val cardclass: String = currentCard.getCardClass()
        val cost: Int = currentCard.getCost()
        val rarity: String = currentCard.getRarity()
        val type: String = currentCard.getType()
        val set: String = currentCard.getSet()
        val collectible: String = currentCard.isCollectible()
        val text: String = currentCard.getText()
        val id: String = currentCard.getId()
        holder.txtHeader.text = name
        holder.txtFooter.text = text
        holder.imgIcon.animation =
            AnimationUtils.loadAnimation(myContext, R.anim.fade_transition_animation)
        holder.itemView.setAnimation(
            AnimationUtils.loadAnimation(
                myContext,
                R.anim.fade_scale_animation
            )
        )
        Picasso.get().load("https://art.hearthstonejson.com/v1/256x/$id.jpg").into(holder.imgIcon)
        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(myContext, CardActivity::class.java)
            intent.putExtra("Cardname", name)
            intent.putExtra("Cardclass", cardclass)
            intent.putExtra("Cardcost", cost)
            intent.putExtra("Cardrarity", rarity)
            intent.putExtra("Cardtype", type)
            intent.putExtra("Cardset", set)
            intent.putExtra("Cardcollectible", collectible)
            intent.putExtra("Cardtext", text)
            intent.putExtra("Cardid", id)
            myContext.startActivity(intent)
        })
        holder.imgFavorite.isSelected = currentCard.isFav()
        holder.imgFavorite.setOnClickListener {
            holder.imgFavorite.isSelected = !holder.imgFavorite.isSelected
            listener.onItemClick(currentCard)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    fun getItemCount(): Int {
        return valuesFiltered.field
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val Key = constraint.toString()
                valuesFiltered = if (Key.isEmpty()) {
                    values
                } else {
                    val lisFiltered: MutableList<Card> = ArrayList<Card>()
                    for (row in values) {
                        if (row.getName().toLowerCase().contains(Key.toLowerCase())) {
                            lisFiltered.add(row)
                        }
                    }
                    lisFiltered
                }
                val filterResults = FilterResults()
                filterResults.values = valuesFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                valuesFiltered = results.values as List<Card>
                notifyDataSetChanged()
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    init {
        values = myDataset
        myContext = context
        this.listener = listener
        valuesFiltered = myDataset
    }
}