//package com.example.eduedu.room
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(
//    entities = [KelasVideo::class], // entities kembali ke nama file KelasVideo,,,kalo mau buat satu entities lagi tinggal kasi koma disamping class didalam kurung siku
//    version = 1
//)
//
//abstract class KelasVideoDB: RoomDatabase() {
//
//    abstract fun kelasVideoDao(): KelasVideoDao
////    abstract fun user(): UserDao // misal kalo mau nambah juga
//
//    companion object{
//
//        @Volatile private var instance: KelasVideoDB? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
//            instance ?: buildDatabase(context).also {
//                instance = it
//            }
//        }
//
//        private fun buildDatabase(context: Context) = Room.databaseBuilder(
//            context.applicationContext,
//            KelasVideoDB::class.java,
//            "kelasVideo111.db" // .db bukanlah sebuah ekstensi, tapi biar keliatan aja kalo ini database
//        ).build()
//    }
//}