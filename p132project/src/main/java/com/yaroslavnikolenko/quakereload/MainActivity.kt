package com.yaroslavnikolenko.quakereload

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.yaroslavnikolenko.quakereload.databinding.ActivityMainBinding
import com.yaroslavnikolenko.quakereload.ui.Map.MapActivity

class MainActivity : AppCompatActivity(){

    private var bindingMainActivity: ActivityMainBinding? = null
    private var appBarConfiguration: AppBarConfiguration? = null
    private var toolBar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainActivity()
        setContentView(bindingMainActivity?.root)

        initNavigation()

        disableNightMode()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration!!) || super.onSupportNavigateUp()
    }

    private fun initNavigation(){
        toolBar = bindingMainActivity?.includeToolBar?.mainToolBar!!
        setSupportActionBar(toolBar)
        val navController = findNavController(R.id.nav_host_fragment)
        bindingMainActivity?.navView?.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, bindingMainActivity?.drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration!!)
    }

    private fun bindingMainActivity(){
        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
    }

    private fun disableNightMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingMainActivity = null
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.mapFragment -> {

                val intent = Intent(this, MapActivity::class.java)

                startActivity(intent)

            }
        }

        return super.onOptionsItemSelected(item)
    }

}