package com.example.hearthstoneapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vogella.android.recyclerview.controller.FavoriteController
import com.vogella.android.recyclerview.controller.MyAdapter
import com.example.hearthstoneapp.model.Card
import xavier.albanet.projetprogrammationmobile.R
import java.util.*

class FavoritesFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var mAdapter: MyAdapter? = null
    private var layoutManager: LayoutManager? = null
    private var controller: FavoriteController? = null
    var sharedPreferences: SharedPreferences? = null
    var rootLayout: ConstraintLayout? = null
    var searchInput: EditText? = null
    var search: CharSequence = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_favorites, container, false)
        recyclerView = view.findViewById<View>(R.id.my_recycler_view_fav) as RecyclerView
        sharedPreferences = context!!.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        controller = FavoriteController(this)
        rootLayout = view.findViewById(R.id.root_layout_fav)
        searchInput = view.findViewById(R.id.search_input_fav)
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mAdapter.getFilter().filter(s)
                search = s
            }

            override fun afterTextChanged(s: Editable) {}
        })
        controller.onStart()
        return view
    }

    fun showListFav(cardList: List<Card>) {
        val listFav: MutableList<Card> = ArrayList<Card>()
        for (card in cardList) {
            if (card.isFav()) {
                listFav.add(card)
            }
        }
        recyclerView.setHasFixedSize(true)
        // use a linear layout manager
        layoutManager = LinearLayoutManager(this.activity)
        recyclerView.setLayoutManager(layoutManager)
        // define an adapter
        mAdapter = MyAdapter(this.activity, listFav, object : OnItemClickListener() {
            fun onItemClick(card: Card?) {
                controller.onClickedFavorite(card)
            }
        })
        recyclerView.setAdapter(mAdapter)
    }

    companion object {
        private const val PREFS = "PREFS"
    }
}