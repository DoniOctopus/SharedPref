package com.enigmacamp.simplesharedpref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.enigmacamp.simplesharedpref.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val USER_NAME = "user"
    private val PASSWORD = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPrefrence = SharedPref(this)

        binding.apply {
            buttonLogin.setOnClickListener {
                val user = userInput.text.toString()
                val password = passwordInput.text.toString()

                sharedPrefrence.save(USER_NAME,user)
                sharedPrefrence.save(PASSWORD,password)

                val result = "${user} ${password}"
                Log.d("SharedPref", "${result}")
            }

            buttonRetrive.setOnClickListener {
                if(sharedPrefrence.retrived(USER_NAME)!=null){
                    userInput.hint = sharedPrefrence.retrived(USER_NAME)!!
                }else{
                    userInput.hint = "No Value Found"
                }

                if(sharedPrefrence.retrived(PASSWORD)!=null){
                    passwordInput.hint = sharedPrefrence.retrived(PASSWORD)!!
                }else{
                    passwordInput.hint = "No Value Found"
                }
            }
        }
    }
}