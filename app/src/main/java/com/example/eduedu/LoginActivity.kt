package com.example.eduedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide title bar
        getSupportActionBar()?.hide()

        // instance EditText
        val emailLayout = findViewById<EditText>(R.id.et_email_login)
        val passwordLayout = findViewById<EditText>(R.id.et_pass_login)

        // instance textview & button
        val btnLogin: Button = findViewById(R.id.btn_Login_login)
        val tvBtnSignup: TextView = findViewById(R.id.tvTobtn_signup_login)

        // event button login
        btnLogin.setOnClickListener{
            // object class database helper
            val databaseHelper = DatabaseHelper(this)

            val email = emailLayout.text.toString().trim()
            val password = passwordLayout.text.toString().trim()

            // check login
            val result: Boolean = databaseHelper.loginCheck(email,password)
            if (result == true){
                Toast.makeText(
                    this@LoginActivity, "Login Berhasil",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                Toast.makeText(
                    this@LoginActivity, "Login Gagal, Coba Lagi!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // event text view sign up
        tvBtnSignup.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))

        }

    }
}