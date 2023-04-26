package com.example.hirehub.ui.hr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hirehub.HireHubApplication
import com.example.hirehub.databinding.ActivityHrHomeBinding
import com.example.hirehub.model.OfferViewModel
import com.example.hirehub.model.OfferViewModelFactory
import com.example.hirehub.model.UserWithOfferViewModel
import com.example.hirehub.model.UserWithOfferViewModelFactory
import com.example.hirehub.model.entities.OfferWithCategory
import com.example.hirehub.model.entities.User
import com.example.hirehub.model.entities.relations.UserOfferPair
import com.example.hirehub.ui.seeker.OfferAdapter
import com.example.hirehub.ui.seeker.OfferDescriptionsActivity
import java.io.Serializable

class HrHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHrHomeBinding

    private val userOfferViewModel: UserWithOfferViewModel by viewModels {
        UserWithOfferViewModelFactory((application as HireHubApplication).userWithOfferRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHrHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        val currentUser = intent.getSerializableExtra("currentUser") as? User

        val recyclerView = binding.rvRequests
        val adapter = RequestAdapter()
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                layoutManager.orientation
            )
        )

        adapter.setOnClickListener(object :
            RequestAdapter.OnClickListener {
            override fun onClick(item: UserOfferPair) {
                val i = Intent(applicationContext, OfferDescriptionsActivity::class.java)
                i.putExtra("Offer", item.offers as Serializable)
                i.putExtra("User", item.user as Serializable)
                i.putExtra("currentUser", currentUser as Serializable)
                startActivity(i)
            }
        })

//        if (currentUser != null)
//            userOfferViewModel.findUser(currentUser.user_id).observe(this) { hr_offers ->
//                val hr_offers_ids = mutableListOf<Int>()
//                if (hr_offers != null)
//                    for (offer in hr_offers.offers)
//                        hr_offers_ids.add(offer.offer_id)
//
//                userOfferViewModel.findUserOfferPairsByUser(currentUser.user_id, hr_offers_ids).observe(this) { usersOffers ->
//                    val finalList = mutableListOf<UserOfferPair>()
//                    if (usersOffers != null)
//                        for(i in usersOffers)
//                            for (offer in i.offers)
//                                finalList.add(UserOfferPair(i.user, mutableListOf(offer)))
//
//                    adapter.submitList(finalList)
//                }
//            }


        binding.fab.setOnClickListener {
            val i = Intent(applicationContext, CreateOfferActivity::class.java)
//            val currentUsername = currentUser.userUsername
//            currentUsername?.let { it1 -> Log.d("currentUserId", it1) }
            i.putExtra("currentUser", currentUser)
            startActivity(i)
        }

        binding.btnAccount.setOnClickListener {
            val i = Intent(applicationContext, AccountHRActivity::class.java)
//            val currentUsername = intent.getStringExtra("currentUserId")
//            currentUsername?.let { it1 -> Log.d("currentUserId", it1) }
            i.putExtra("currentUser", currentUser)
            startActivity(i)
        }
    }
}