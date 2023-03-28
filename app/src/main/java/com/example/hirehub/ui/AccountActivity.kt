package com.example.hirehub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hirehub.R
import com.example.hirehub.databinding.ActivityAccountBinding
import com.example.hirehub.databinding.ActivitySeekerHomeBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnHome.setOnClickListener {
            val i = Intent(applicationContext, SeekerHomeActivity::class.java)
            startActivity(i)
        }

        binding.btnSearch.setOnClickListener {
            val i = Intent(applicationContext, SearchActivity::class.java)
            startActivity(i)
        }
    }
}