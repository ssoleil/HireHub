package com.example.hirehub.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hirehub.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            if (binding.etUsername.text.isEmpty()) {
                binding.etUsername.error = "Username is required!"
            }
            else if (binding.etPwd.text.isEmpty()) {
                binding.etUsername.error = "Password is required"
            } else {
                // todo: check before
                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
            }
        }

        binding.tvRegister.setOnClickListener {
            // todo: check before
            val i = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(i)
        }
    }

}