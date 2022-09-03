package com.example.eduedu.admin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.eduedu.DatabaseHelper
import com.example.eduedu.HomeActivity
import com.example.eduedu.R
import com.example.eduedu.model.KelasModel

class EditKelasActivity : AppCompatActivity() {

    lateinit var image : ImageView
    companion object{
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_kelas)

        // hide title bar
        supportActionBar?.hide()

        // INSTANCE
        image = findViewById(R.id.iv_imagekelas)
        val textTitle = findViewById<EditText>(R.id.et_writeTitlekelas)
        val textPrice = findViewById<EditText>(R.id.et_writePriceKelas)
        val textDeskripsi = findViewById<EditText>(R.id.et_writeDeskripsiKelas)
        val btnAddImage = findViewById<Button>(R.id.btn_addImageKelas)
        val btnSaveKelas = findViewById<Button>(R.id.btn_saveDataKelas)

        // event for button add image (pick image from galery
        btnAddImage.setOnClickListener{
            pickImageGalery() // sebuah function untuk ambil imgae from galery
        }
        val databaseHelper = DatabaseHelper(this)

        val extras = intent.extras
        if (extras != null){
            val kelasId = extras.getInt("id")

            if (kelasId != null){
                val result: ArrayList<KelasModel> = databaseHelper.getKelas(kelasId)

                textTitle.setText(result[0].title)
                textPrice.setText(result[0].price.toString())
                textDeskripsi.setText(result[0].deskripsi)
                image.setImageBitmap(result[0].image)
            }
        }

        btnSaveKelas.setOnClickListener {
            val kelasId = extras?.getInt("id")
            val databaseHelper = DatabaseHelper(this)

            val title : String = textTitle.text.toString().trim()
            val price : Int = textPrice.text.toString().toInt()
            val deskripsi : String = textDeskripsi.text.toString().trim()
            val bitmapDrawable : BitmapDrawable = image.drawable as BitmapDrawable
            val bitmap : Bitmap = bitmapDrawable.bitmap

            val kelasModel = KelasModel(kelasId!!.toInt(),title,price,deskripsi,bitmap)
            if (kelasId != null) {
                databaseHelper.updateKelas(kelasModel, kelasId.toString())
            }
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
    private fun pickImageGalery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/"
        startActivityForResult(intent, AddKelasActivity.IMAGE_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        image = findViewById(R.id.iv_imagekelas)
        val code = AddKelasActivity.IMAGE_REQUEST_CODE.toInt()
        if(requestCode == code && resultCode == Activity.RESULT_OK){
            image.setImageURI(data?.data)
        }
    }
}