package com.example.hearthstoneapp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.hearthstoneapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vogella.android.recyclerview.controller.MainController
import xavier.albanet.projetprogrammationmobile.R

class MainActivity : FragmentActivity() {
    private var controller: MainController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(R.id.fragment_container, HomeFragment())
        ft.commit()
        controller = MainController(this)
        controller.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : OnNavigationItemSelectedListener() {
            fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                var selectedFragment: Fragment? = null
                when (menuItem.itemId) {
                    R.id.nav_home -> selectedFragment = HomeFragment()
                    R.id.nav_favorites -> selectedFragment = FavoritesFragment()
                }
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                ft.replace(R.id.fragment_container, selectedFragment!!)
                ft.commit()
                return true
            }
        }
}