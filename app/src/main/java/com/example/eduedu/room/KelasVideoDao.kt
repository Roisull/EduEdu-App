//package com.example.eduedu.room
//
//import androidx.room.*
//
//@Dao
//interface KelasVideoDao {
//
//    // menambahkan suspend,,,karna kita menggunakan coroutines
//    @Insert
//    suspend fun addKelasVideo(kelasVideo: KelasVideo)
//
//    @Update
//    suspend fun updateKelasVideo(kelasVideo: KelasVideo)
//
//    @Delete
//    suspend fun deleteKelasVideo(kelasVideo: KelasVideo)
//
//    @Query("SELECT * FROM kelasVideo")
//    suspend fun getKelasVideo(): List<KelasVideo>
//}