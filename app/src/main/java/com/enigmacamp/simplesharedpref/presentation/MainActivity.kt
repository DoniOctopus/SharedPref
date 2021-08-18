package com.enigmacamp.simplesharedpref.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.simplesharedpref.data.SharedPref
import com.enigmacamp.simplesharedpref.data.api.RetrofitInstance
import com.enigmacamp.simplesharedpref.data.repository.AuthenticationRepositoryImpl
import com.enigmacamp.simplesharedpref.databinding.ActivityMainBinding
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    private val USER_NAME = "user"
    private val PASSWORD = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()

//        val sharedPrefrence = SharedPref(this)

        binding.apply {
            buttonLogin.setOnClickListener {
                val user = userInput.text.toString()
                val password = passwordInput.text.toString()
                val result = viewModel.doLogin(user, password)
/*                sharedPrefrence.save(USER_NAME, user)
                sharedPrefrence.save(PASSWORD, password)

                val result = "${user} ${password}"*/
                Log.d("SharedPref", "${result}")
            }

/*            buttonRetrive.setOnClickListener {
                if (sharedPrefrence.retrived(USER_NAME) != null) {
                    userInput.hint = sharedPrefrence.retrived(USER_NAME)!!
                } else {
                    userInput.hint = "No Value Found"
                }

                if (sharedPrefrence.retrived(PASSWORD) != null) {
                    passwordInput.hint = sharedPrefrence.retrived(PASSWORD)!!
                } else {
                    passwordInput.hint = "No Value Found"
                }
            }*/
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val sharedPref = SharedPref(applicationContext)
                val authenticationRepository =
                    AuthenticationRepositoryImpl(
                        RetrofitInstance(sharedPref).authApi,
                        sharedPref
                    )
                return MainActivityViewModel(authenticationRepository) as T
            }

        }).get(MainActivityViewModel::class.java)
    }
}