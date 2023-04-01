package com.example.hirehub.ui.hr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hirehub.databinding.ActivityCreateOfferBinding

class CreateOfferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateOfferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateOfferBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}