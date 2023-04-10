package com.example.hirehub.ui.seeker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hirehub.R
import com.example.hirehub.databinding.ActivityAccountSeekerBinding
import com.example.hirehub.databinding.ActivitySeekerRequestsBinding

class AccountSeekerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountSeekerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountSeekerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}