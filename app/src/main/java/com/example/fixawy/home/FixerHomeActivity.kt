package com.example.fixawy.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.base.BaseActivity
import com.example.fixawy.R
import kotlinx.android.synthetic.main.activity_fixer_home.*
import kotlinx.android.synthetic.main.activity_home.*

class FixerHomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixer_home)
        setContainerView(R.id.fixer_home_layout)
        initBottomNav()
    }

    private fun initBottomNav() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fixer_home_frag_host) as NavHostFragment?
        NavigationUI.setupWithNavController(
            fixer_bottom_nav_bar,
            navHostFragment!!.navController
        )
    }
}