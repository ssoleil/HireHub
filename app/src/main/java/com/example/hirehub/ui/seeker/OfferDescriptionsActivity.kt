package com.example.hirehub.ui.seeker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfferDescriptionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val offer = intent.getSerializableExtra("Offer") as? OfferWithCategory
        val currentUser = intent.getSerializableExtra("currentUser") as? User

        binding.offerNameTitle.text = offer?.offer?.offerName ?: ""
        binding.tvOfferDescription.text = offer?.offer?.offerDescription ?: ""
        binding.category.text = offer?.offerCategory?.categoryName ?: ""
        binding.companyName.text = offer?.offer?.offerCompanyName ?: ""
        binding.positionName.text = offer?.offer?.offerPosition ?: ""

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnApply.setOnClickListener {
            //insert to the n:m table with user id and offer id

            if (offer != null && currentUser != null) {
                val join = UserWithOffer(currentUser.user_id, offer.offer.offer_id)
                userWithOfferViewModel.insert(join)
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
            }
        }

    }
}