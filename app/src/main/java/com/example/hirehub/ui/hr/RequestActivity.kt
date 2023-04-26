package com.example.hirehub.ui.hr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hirehub.R
import com.example.hirehub.databinding.ActivityCreateOfferBinding
import com.example.hirehub.databinding.ActivityRequestBinding
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.User

class RequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val currentUser = intent.getSerializableExtra("currentUser") as? User
        val user = intent.getSerializableExtra("User") as? User
        val offer = intent.getSerializableExtra("Offer") as? Offer

        // bind

        binding.btnBack.setOnClickListener {
            Log.d("RequestActivity", currentUser?.userUsername + user?.userUsername + offer?.offerName)
            finish()
        }

        binding.btnAccept.setOnClickListener {
            //
        }

        binding.btnReject.setOnClickListener {
            //
        }
    }
}