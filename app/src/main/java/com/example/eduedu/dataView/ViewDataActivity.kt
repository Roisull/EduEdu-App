package com.example.eduedu.dataView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.eduedu.R
import com.example.eduedu.dataView.dataFragment.DataKelasFragment
import com.example.eduedu.dataView.dataFragment.DataUserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ViewDataActivity : AppCompatActivity() {

    // for Top nav in view data activity
    private val dataKelasFragment = DataKelasFragment()
    private val dataUserFragment = DataUserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)

        // for Top mav in view data activity
        val topNavigation = findViewById<BottomNavigationView>(R.id.top_navigation)
        replaceDataFragment(dataKelasFragment)
        topNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.it_data_kelas -> replaceDataFragment(dataKelasFragment)
                R.id.it_data_user -> replaceDataFragment(dataUserFragment)
            }

            true
        }
    }

    // for Top nav in view data activity
    private fun replaceDataFragment(fragment: Fragment){
        if (fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_data, fragment)
            transaction.commit()
        }
    }
}