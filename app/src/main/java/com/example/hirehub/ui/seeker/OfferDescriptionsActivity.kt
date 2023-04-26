package com.example.hirehub.ui.seeker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.hirehub.HireHubApplication
import com.example.hirehub.R
import com.example.hirehub.databinding.ActivityOfferDescriptionsBinding
import com.example.hirehub.databinding.ActivitySeekerHomeBinding
import com.example.hirehub.model.UserViewModel
import com.example.hirehub.model.UserViewModelFactory
import com.example.hirehub.model.UserWithOfferViewModel
import com.example.hirehub.model.UserWithOfferViewModelFactory
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferWithCategory
import com.example.hirehub.model.entities.User
import com.example.hirehub.model.entities.relations.UserWithOffer

class OfferDescriptionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOfferDescriptionsBinding

    private val userWithOfferViewModel: UserWithOfferViewModel by viewModels {
        UserWithOfferViewModelFactory((application as HireHubApplication).userWithOfferRepository)
    }

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as HireHubApplication).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfferDescriptionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val offer = intent.getSerializableExtra("Offer") as? OfferWithCategory
        val currentUser = intent.getSerializableExtra("currentUser") as? User

        binding.offerNameTitle.text = offer?.offer_name ?: ""
        binding.tvOfferDescription.text = offer?.offer_description ?: ""
        binding.category.text = offer?.category_name ?: ""
        binding.companyName.text = offer?.offer_company_name ?: ""
        binding.positionName.text = offer?.offer_position ?: ""

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnApply.setOnClickListener {
            //insert to the n:m table with user id and offer id

            if (offer != null && currentUser != null) {
                userViewModel.findUser(currentUser.userUsername).observe(this) { user ->
                    if (user != null) {
                        val join = UserWithOffer(user.user_id, offer.offer_id)
                        Log.d("Offer", user.user_id.toString() + " " + offer.offer_id.toString())
                        userWithOfferViewModel.insert(join)
                        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
            }
        }

    }
}