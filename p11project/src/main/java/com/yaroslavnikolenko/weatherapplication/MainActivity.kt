package com.yaroslavnikolenko.weatherapplication

import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yaroslavnikolenko.weatherapplication.databinding.ActivityMainBinding
import com.yaroslavnikolenko.weatherapplication.ui.twenyFourWeather.TwentyFourWeather

class MainActivity : AppCompatActivity() {

    private var appBarConfiguration: AppBarConfiguration? = null
    private var binding: ActivityMainBinding? = null
    private var toolbarText: TextView? = null
    private var twentyFourWeather: TwentyFourWeather? = null
    var navBottomView : BottomNavigationView? = null
    var drawerLayout: DrawerLayout? = null
    var navView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind()
        initUI()
        navigation()
        observe()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration!!) || super.onSupportNavigateUp()

    }

    private fun initUI(){
        toolbarText = findViewById(R.id.toolbarTextView)
        navBottomView = findViewById(R.id.bottomNavigationView)
    }

    private fun bind(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.appBarMain?.toolbar)

        drawerLayout = binding!!.drawerLayout
        navView = binding!!.navView
    }

    private fun navigation(){
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_report, R.id.nav_share, R.id.WeatherTypeFragment, R.id.WeatherTodayFragment, R.id.WeatherRadarFragment, R.id.WeatherForecastFragment
            ), drawerLayout
        )

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        setupActionBarWithNavController(navController, appBarConfiguration!!)
        navView?.setupWithNavController(navController)
        navBottomView?.setupWithNavController(navController)
    }

    private fun observe(){
        twentyFourWeather = TwentyFourWeather()
        twentyFourWeather?.resultLocation?.observe(this){
            toolbarText?.text = it.region
        }
    }

}