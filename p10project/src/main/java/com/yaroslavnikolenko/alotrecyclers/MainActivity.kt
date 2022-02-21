package com.yaroslavnikolenko.alotrecyclers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var bottomNavigationView: BottomNavigationView? = null
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView!!.setupWithNavController(navController!!)

        setupActionBarWithNavController(navController!!)

    }

}