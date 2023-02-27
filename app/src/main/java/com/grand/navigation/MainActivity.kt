package com.grand.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.grand.navigation.ui.onBoarding.FirstUsing
import com.grand.navigation.ui.onBoarding.OnboardingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val used = FirstUsing(this)
        if (used.getAppUsed()){
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.orderFragment, R.id.profileFragment, R.id.paymentFragment, R.id.deliveryFragment,
                R.id.contactFragment, R.id.settingsFragment, R.id.helpFragment),
            drawerLayout
        )

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}