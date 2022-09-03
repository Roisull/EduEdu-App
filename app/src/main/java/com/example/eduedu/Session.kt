package com.example.eduedu

import android.content.Context
import android.content.SharedPreferences

class Session(context: Context) {
    val PRIVATE_MODE = 0

    private val PREF_NAME = "SharedPreferences"

    private val ID = "id"

    var pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
    var editor:SharedPreferences.Editor? = pref?.edit()

    fun setId(id: Int){
        editor?.putInt(ID,id)
        editor?.commit()
    }

    fun getId(): Int?{
        return pref?.getInt(ID,0)
    }

    fun removeId(){
        editor?.clear()
        editor?.commit()
    }
}