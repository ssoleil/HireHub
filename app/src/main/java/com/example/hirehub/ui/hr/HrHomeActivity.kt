package com.example.hirehub.ui.hr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.hirehub.databinding.ActivityHrHomeBinding

class HrHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHrHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHrHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            val i = Intent(applicationContext, CreateOfferActivity::class.java)
            val currentUsername = intent.getStringExtra("currentUserId")
            currentUsername?.let { it1 -> Log.d("currentUserId", it1) }
            i.putExtra("currentUserId", currentUsername)
            startActivity(i)
        }

        binding.btnAccount.setOnClickListener {
            val i = Intent(applicationContext, AccountHRActivity::class.java)
            val currentUsername = intent.getStringExtra("currentUserId")
            currentUsername?.let { it1 -> Log.d("currentUserId", it1) }
            i.putExtra("currentUserId", intent.getStringExtra("currentUserId"))
            startActivity(i)
        }
    }
}