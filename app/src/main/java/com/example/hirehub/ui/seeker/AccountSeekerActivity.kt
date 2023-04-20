package com.example.hirehub.ui.seeker

import android.content.Intent
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