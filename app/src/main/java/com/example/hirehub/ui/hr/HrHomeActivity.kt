package com.example.hirehub.ui.hr

import android.content.Intent
import android.os.Bundle
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
            startActivity(i)
        }

        binding.btnAccount.setOnClickListener {
            val i = Intent(applicationContext, AccountHRActivity::class.java)
            startActivity(i)
        }
    }
}