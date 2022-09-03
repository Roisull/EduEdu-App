package com.example.eduedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.eduedu.Fragments.AccountFragment
import com.example.eduedu.Fragments.FavoriteFragment
import com.example.eduedu.Fragments.HomeFragment
import com.example.eduedu.Fragments.ReadFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    // for bottom nav
    private val homeFragment = HomeFragment()
    private val readFragment = ReadFragment()
    private val favoriteFragment = FavoriteFragment()
    private val accountFragment = AccountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // for bottom navigation
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        replaceFragment(homeFragment)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.it_home -> replaceFragment(homeFragment)
                R.id.it_read_book -> replaceFragment(readFragment)
                R.id.it_pin_book -> replaceFragment(favoriteFragment)
                R.id.it_account -> replaceFragment(accountFragment)
            }
            true
        }
    }

    // for bottom navigation
    private fun replaceFragment(fragment: Fragment){
        if (fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}