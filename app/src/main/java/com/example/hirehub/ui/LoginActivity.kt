package com.example.hirehub.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hirehub.HireHubApplication
import com.example.hirehub.databinding.ActivityLoginBinding
import com.example.hirehub.model.UserViewModel
import com.example.hirehub.model.UserViewModelFactory
import com.example.hirehub.ui.seeker.SeekerHomeActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as HireHubApplication).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {

            val username = binding.etUsername
            val pwd = binding.etPwd

            if (username.text.isEmpty())
                username.error = "Username is required!"

            else if (pwd.text.isEmpty())
                pwd.error = "Password is required"

            else {
                userViewModel.findUser(username.text.toString(), pwd.text.toString()).observe(this) { user ->
                    //if we don't have an account
                    Log.d("LoginActivity", user?.userName.toString())
                    when {
                        user == null -> {
                            Toast.makeText(this, "User is not found", Toast.LENGTH_SHORT).show()
                        }
                        user.userStatus == "seeker" -> {
                            //todo: save current user
                            val i = Intent(applicationContext, SeekerHomeActivity::class.java)
                            startActivity(i)
                        }
                        user.userStatus == "hr" -> {
                            //todo: save current user
                            //todo: hr home screen
//                            val i = Intent(applicationContext, SeekerHomeActivity::class.java)
//                            startActivity(i)
                        }
                        else -> {
                            Log.d("LoginActivity", "else")
                        }
                    }
                }
            }
        }

        binding.tvRegister.setOnClickListener {
            val i = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(i)
        }
    }
}