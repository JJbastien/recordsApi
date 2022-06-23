package com.example.recordsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recordsapi.R
import com.example.recordsapi.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val topNavigationView  = findViewById<BottomNavigationView>(R.id.top_nav)
            //val navController = findNavController(R.id.fragmentContainerView)
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController

            val appBarConfiguration = AppBarConfiguration(setOf(R.id.rock_layout_fragment, R.id.class_layout_fragment, R.id.pop_layout_fragment))
            setupActionBarWithNavController(navController, appBarConfiguration)
            topNavigationView.setupWithNavController(navController)
    }
}