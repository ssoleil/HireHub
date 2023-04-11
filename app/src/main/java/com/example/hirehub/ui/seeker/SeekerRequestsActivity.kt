package com.example.hirehub.ui.seeker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hirehub.HireHubApplication
import com.example.hirehub.R
import com.example.hirehub.databinding.ActivitySeekerHomeBinding
import com.example.hirehub.databinding.ActivitySeekerRequestsBinding
import com.example.hirehub.model.OfferViewModel
import com.example.hirehub.model.OfferViewModelFactory
import com.example.hirehub.model.UserWithOfferViewModel
import com.example.hirehub.model.UserWithOfferViewModelFactory
import com.example.hirehub.model.entities.User

class SeekerRequestsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeekerRequestsBinding

    private val offerViewModel: OfferViewModel by viewModels {
        OfferViewModelFactory((application as HireHubApplication).offerRepository)
    }

    private val userWithOfferViewModel: UserWithOfferViewModel by viewModels {
        UserWithOfferViewModelFactory((application as HireHubApplication).userWithOfferRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeekerRequestsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val recyclerView = binding.rvSeekerRequests
        val adapter = RequestAdapter()
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val currentUser = intent.getSerializableExtra("currentUser") as? User

        currentUser?.user_id?.let { user ->
            userWithOfferViewModel.findUser(user).observe(this) { offers ->
                offers.let { adapter.submitList(it?.offers) }
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}