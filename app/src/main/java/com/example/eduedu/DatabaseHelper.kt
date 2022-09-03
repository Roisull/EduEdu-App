package com.example.eduedu

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.database.CursorWindow
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import com.example.eduedu.Sessions.LoginPref
import com.example.eduedu.admin.AddKelasActivity
import com.example.eduedu.model.KelasModel
import com.example.eduedu.model.UserModel
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.reflect.Field
import kotlin.collections.ArrayList

class DatabaseHelper(context: Context): SQLiteOpenHelper(
context,DATABASE_NAME,null,DATABASE_VERSION
) {
//    private val session: Session = Session(context)//global variable//in oncreate
    companion object{
        private val DATABASE_NAME = "eduedu"
        private val DATABASE_VERSION = 2
        // table user
        private val TABLE_USERS = "user"
        // column table user
        private val COLUMN_EMAIL = "email"
        private val COLUMN_NAME = "name"
        private val COLUMN_NO_HP = "no_hp"
        private val COLUMN_PASSWORD = "password"

        // table kelas
        private val TABLE_KELAS = "kelas"
        // column kelas table
        private val COLUMN_ID_KELAS = "idKelas"
        private val COLUMN_TITLE_KELAS = "kelasTitle"
        private val COLUMN_PRICE_KELAS = "price"
        private val COLUMN_DESKRIPSI_KELAS = "deskripsi"
        private val COLUMN_IMAGE_KELAS = "photo"
    }

    // create table user sql query
    private val CREATE_USERS_TABLE = ("CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_EMAIL + " TEXT PRIMARY KEY, " + COLUMN_NAME + " TEXT, "
            + COLUMN_NO_HP + " TEXT, " + COLUMN_PASSWORD + " TEXT)")

    // Create table kelas sql query
    private val CREATE_KELAS_TABLE = ("CREATE TABLE " + TABLE_KELAS + "("
            + COLUMN_ID_KELAS + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE_KELAS + " TEXT, "
            + COLUMN_PRICE_KELAS + " INTEGER, " + COLUMN_DESKRIPSI_KELAS + " TEXT, " + COLUMN_IMAGE_KELAS + " BLOB)")

    // drop table user sql query
    private val DROP_USERS_TABLE = "DROP TABLE IF EXISTS $TABLE_USERS"

    // drop table kelas sql query
    private val DROP_KELAS_TABLE = "DROP TABLE IF EXISTS $TABLE_KELAS"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(CREATE_USERS_TABLE)
        p0?.execSQL(CREATE_KELAS_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(DROP_USERS_TABLE)
        p0?.execSQL(DROP_KELAS_TABLE)
        onCreate(p0)
    }

    // login check
    fun loginCheck(email: String, password: String): Boolean{
        val columns = arrayOf(COLUMN_NAME)
        val db = this.readableDatabase

        // selection criteria
        val selection = "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
        // selection arguments
        val selectionArgs = arrayOf(email,password)

        val cursor = db.query(TABLE_USERS, // table to query
        columns, // columns to return
        selection, // columns for WHERE clause
        selectionArgs, // values for the WHERE clause
        null,
            null,
            null
        )

        val cursorCount = cursor.count
        cursor.close()
        db.close()
        return cursorCount > 0
    }

    // add user
    fun addUsers(email: String, name: String, no_hp: String, password: String){
        val db = this.readableDatabase

        val values = ContentValues()
        values.put(COLUMN_EMAIL, email)
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_NO_HP, no_hp)
        values.put(COLUMN_PASSWORD, password)

        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    // check data
    fun checkData(email: String): String{
        val columns = arrayOf(COLUMN_NAME)
        val db = this.readableDatabase
        val selection = "$COLUMN_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        var name: String = ""

        val cursor = db.query(TABLE_USERS, // table to query
        columns, // columns to return
        selection, // columns for WHERE clause
        selectionArgs, // the values for the WHERE clause
        null,
            null,
            null
        )

        if (cursor.moveToFirst()){
            name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
        }
        cursor.close()
        db.close()
        return name
    }

    // add kelas
    fun addKelas(kelas:KelasModel): Long {

        // prepare
        val db = this.readableDatabase
        val values = ContentValues()
        values.put(COLUMN_TITLE_KELAS, kelas.title)
        values.put(COLUMN_PRICE_KELAS, kelas.price)
        values.put(COLUMN_DESKRIPSI_KELAS, kelas.deskripsi)

        // prepare image
        val byteOutputStream = ByteArrayOutputStream()
        val imageInByte:ByteArray
        kelas.image.compress(Bitmap.CompressFormat.JPEG, 100, byteOutputStream)
        imageInByte = byteOutputStream.toByteArray()
        values.put(COLUMN_IMAGE_KELAS, imageInByte)

        var result = db.insert(TABLE_KELAS, null, values)

//        // show message
//        if (result == (0).toLong()){
//            Toast.makeText("Add menu Failed", Toast.LENGTH_SHORT).show()
//        }else{
//            Toast.makeText("Add menu Success", Toast.LENGTH_SHORT).show()
//        }
        db.close()

        return result
    }

    // function for show data kelas
    @SuppressLint("Range", "DiscouragedPrivateApi")
    fun showKelas():ArrayList<KelasModel>{

//        val id:Int
//        id = session.getId()!!
        val listModel = ArrayList<KelasModel>()
        val db = this.readableDatabase
        var cursor:Cursor?=null
        try{
            cursor = db.rawQuery("SELECT * FROM $TABLE_KELAS", null)

            // menetapkan maksimal size dan resolusi gambar
            val field: Field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
            field.isAccessible = true
            field.set(null,100 * 1024 * 1024) // size 100mb, resolusi 1024 * 1024
        }catch (se:SQLiteException){
            db.execSQL(CREATE_KELAS_TABLE)
            return ArrayList()
        }

//        var id: Int
        var kelasId: Int
        var title: String
        var price: Int
        var deskripsi: String
        var imageArray: ByteArray
        var imageBmp: Bitmap
        if (cursor.moveToFirst()){
            do {
                // get data text
                kelasId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_KELAS))
                title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE_KELAS))
                deskripsi = cursor.getString(cursor.getColumnIndex(COLUMN_DESKRIPSI_KELAS))
                price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE_KELAS))
                // get data image
                imageArray = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE_KELAS))
                // convert ByteArray to Bitmap
                val byteInputStream = ByteArrayInputStream(imageArray)
                imageBmp = BitmapFactory.decodeStream(byteInputStream)
                val model = KelasModel(kelasId = kelasId, title = title, price = price, deskripsi = deskripsi, image = imageBmp)
                listModel.add(model)
            }while (cursor.moveToNext())
        }
        return listModel
    }

    fun deleteKelas(kelasId: Int): Int {
        val db = this.readableDatabase

        val result = db.delete(TABLE_KELAS, "$COLUMN_ID_KELAS = $kelasId", null)

        return result
        db.close()
    }

    fun getKelas(kelasId: Int): ArrayList<KelasModel>{
        val db = this.readableDatabase
        val listModel = arrayListOf<KelasModel>()
        val selectQuery = "SELECT  * FROM $TABLE_KELAS WHERE $COLUMN_ID_KELAS = ?"

        var id: Int
        var title: String
        var price: Int
        var deskripsi: String
        var imageArray:ByteArray
        var imageBmp: Bitmap

        db.rawQuery(selectQuery, arrayOf(kelasId.toString())).use{
            if (it.moveToFirst()){
                id = it.getInt(it.getColumnIndexOrThrow(COLUMN_ID_KELAS))
                title = it.getString(it.getColumnIndexOrThrow(COLUMN_TITLE_KELAS))
                price = it.getInt(it.getColumnIndexOrThrow(COLUMN_PRICE_KELAS))
                deskripsi = it.getString(it.getColumnIndexOrThrow(COLUMN_DESKRIPSI_KELAS))
                imageArray = it.getBlob(it.getColumnIndexOrThrow(COLUMN_IMAGE_KELAS))
                val byteInputStream = ByteArrayInputStream(imageArray)
                imageBmp = BitmapFactory.decodeStream(byteInputStream)

                val model = KelasModel(kelasId = id, title = title, price = price, deskripsi = deskripsi, image = imageBmp)
                listModel.add(model)
            }
        }

        return listModel
    }

    fun updateKelas(kelas: KelasModel, kelasId: String): Int{
        val db = this.readableDatabase
        val values = ContentValues()

        values.put(COLUMN_TITLE_KELAS, kelas.title)
        values.put(COLUMN_PRICE_KELAS, kelas.price)
        values.put(COLUMN_DESKRIPSI_KELAS, kelas.deskripsi)

        val byteOutputStream = ByteArrayOutputStream()
        val imageInByte:ByteArray
        kelas.image.compress(Bitmap.CompressFormat.JPEG, 100, byteOutputStream)
        imageInByte = byteOutputStream.toByteArray()
        values.put(COLUMN_IMAGE_KELAS, imageInByte)

        val result = db.update(TABLE_KELAS, values, "$COLUMN_ID_KELAS= $kelasId",null)

        return result
        db.close()
    }
}