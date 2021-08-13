package com.enigmacamp.simplesharedpref

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    val sharedPref : SharedPreferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

    fun save(KEY_NAME : String , text : String){
        val saveData : SharedPreferences.Editor = sharedPref.edit()
        saveData.putString(KEY_NAME,text)
        saveData.commit()
    }

    fun retrived(KEY_NAME: String) : String?{
        return sharedPref.getString(KEY_NAME,null)
    }
}