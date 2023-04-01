package com.example.hirehub.ui.hr

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hirehub.databinding.ActivityAccountHrBinding

class AccountHRActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountHrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountHrBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCroissant.setOnClickListener {
            Toast.makeText(this, "You are a sweet croissant", Toast.LENGTH_SHORT).show()
        }


    }
}