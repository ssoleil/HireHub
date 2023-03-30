package com.example.hirehub.ui.hr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hirehub.R
import com.example.hirehub.databinding.ActivityHrHomeBinding
import com.example.hirehub.databinding.ActivitySeekerHomeBinding

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
//            val i = Intent(applicationContext, HrAccountActivity::class.java)
//            startActivity(i)
        }
    }
}