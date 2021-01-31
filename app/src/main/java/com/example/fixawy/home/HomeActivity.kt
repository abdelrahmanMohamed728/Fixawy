package com.example.fixawy.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.fixawy.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initBottomNav()
    }

    private fun initBottomNav() {
        val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.home_frag_host) as NavHostFragment?
        NavigationUI.setupWithNavController(
                bottom_nav_bar,
                navHostFragment!!.navController
        )
    }
}