package com.example.eduedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //hide title bar
        getSupportActionBar()?.hide()

        // instance edit text & button
        val etEmailRegister = findViewById<EditText>(R.id.et_email_register)
        val etNamaLengkapRegister = findViewById<EditText>(R.id.et_namaLengkap_register)
        val etNoHpRegister = findViewById<EditText>(R.id.et_noHp_register)
        val etPassRegister = findViewById<EditText>(R.id.et_pass_register)
        val btnRegsiter = findViewById<Button>(R.id.btn_Register_register)

        // event button register
        btnRegsiter.setOnClickListener{
            // object class database helper
            val databaseHelper = DatabaseHelper(this)

            val email = etEmailRegister.text.toString().trim()
            val name = etNamaLengkapRegister.text.toString().trim()
            val noHp = etNoHpRegister.text.toString().trim()
            val password = etPassRegister.text.toString().trim()

            // email validator
            if (name.isEmpty()){
                Toast.makeText(this, "Masukkan Nama !", Toast.LENGTH_SHORT).show()
            }else if(noHp.isEmpty()){
                Toast.makeText(this, "Masukkan No Hp !", Toast.LENGTH_SHORT).show()
            }else if (email.isEmpty()){
                Toast.makeText(this, "Masukkan Email !", Toast.LENGTH_SHORT).show()
            }else if(password.isEmpty()){
                Toast.makeText(this, "Masukkan password !", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "Silahkan Login", Toast.LENGTH_SHORT).show()

                databaseHelper.addUsers(email,
                    name,noHp, password)

                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }
}