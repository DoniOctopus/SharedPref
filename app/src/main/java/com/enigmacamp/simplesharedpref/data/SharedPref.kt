package com.enigmacamp.simplesharedpref.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SharedPref(context: Context) {
    //    val sharedPref : SharedPreferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    val sharedPref: SharedPreferences = EncryptedSharedPreferences.create(
        "SharedPref",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun save(KEY_NAME: String, text: String) {
        val saveData: SharedPreferences.Editor = sharedPref.edit()
        saveData.putString(KEY_NAME, text)
        saveData.commit()
    }

    fun retrived(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, null)
    }
}