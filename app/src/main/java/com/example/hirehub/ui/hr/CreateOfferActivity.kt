package com.example.hirehub.ui.hr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hirehub.R
import com.example.hirehub.databinding.ActivityCreateOfferBinding
import com.example.hirehub.databinding.ActivityHrHomeBinding

class CreateOfferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateOfferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateOfferBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}