package com.example.hirehub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hirehub.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            if (binding.etName.text.isEmpty()) {
                binding.etName.error = "Name is required!"
            } else if (binding.etUsername.text.isEmpty()) {
                binding.etUsername.error = "Username is required"
            } else if (binding.etPwd.text.isEmpty()) {
                binding.etPwd.error = "Password is required"
            } else {
                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
            }
        }

        binding.tvLogin.setOnClickListener {
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)
        }

    }
}