package com.example.hirehub.ui.seeker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hirehub.databinding.ActivityAccountBinding

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