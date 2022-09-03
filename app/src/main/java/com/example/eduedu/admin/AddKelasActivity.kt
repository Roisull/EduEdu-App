package com.example.eduedu.admin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.room.Database
import com.example.eduedu.DatabaseHelper
import com.example.eduedu.HomeActivity
import com.example.eduedu.R
import com.example.eduedu.Session
import com.example.eduedu.dataView.ViewDataActivity
import com.example.eduedu.model.KelasModel
import kotlinx.android.synthetic.main.fragment_home.view.*

class AddKelasActivity : AppCompatActivity() {

    lateinit var image :ImageView
    companion object{
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_kelas)

        // hide title bar
        supportActionBar?.hide()

//        // session
//        val session: Session = Session(this)

        // INSTANCE
        image = findViewById(R.id.iv_imagekelas)
        val textTitle = findViewById<EditText>(R.id.et_writeTitlekelas)
        val textPrice = findViewById<EditText>(R.id.et_writePriceKelas)
        val textDeskripsi = findViewById<EditText>(R.id.et_writeDeskripsiKelas)
        val btnAddImage = findViewById<Button>(R.id.btn_addImageKelas)
        val btnSaveKelas = findViewById<Button>(R.id.btn_saveDataKelas)
        val tempatImage = image

        // event for button add image (pick image from galery
        btnAddImage.setOnClickListener{
            pickImageGalery() // sebuah function untuk ambil imgae from galery
        }

        // event when button save on click
        btnSaveKelas.setOnClickListener{
            // object class databaseHelper
            val databaseHelper = DatabaseHelper(this)

//            val id: Int = session.getId()!!
            val title: String = textTitle.text.toString().trim()
            val price: Int = textPrice.text.toString().toInt()
            val deskripsi: String = textDeskripsi.text.toString().trim()
            val bitmapDrawable: BitmapDrawable = image.drawable as BitmapDrawable
            val bitmap: Bitmap = bitmapDrawable.bitmap

            val kelasModel = KelasModel(0,title,price,deskripsi,bitmap)
            val result = databaseHelper.addKelas(kelasModel)
            // show message
            if (result == (0).toLong()){
            Toast.makeText(this@AddKelasActivity,"Add kelas Failed", Toast.LENGTH_SHORT).show()
            }else{
            Toast.makeText(this@AddKelasActivity,"Add kelas Success", Toast.LENGTH_SHORT).show()
            }

            textTitle.text.clear()
            textPrice.text.clear()
            textDeskripsi.text.clear()
            // kurang clear image
        }
    }

    private fun pickImageGalery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            image.setImageURI(data?.data)
        }
    }
}