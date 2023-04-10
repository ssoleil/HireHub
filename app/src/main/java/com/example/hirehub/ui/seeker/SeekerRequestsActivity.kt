package com.example.hirehub.ui.seeker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hirehub.R
import com.example.hirehub.databinding.ActivitySeekerHomeBinding
import com.example.hirehub.databinding.ActivitySeekerRequestsBinding

class SeekerRequestsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeekerRequestsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeekerRequestsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}