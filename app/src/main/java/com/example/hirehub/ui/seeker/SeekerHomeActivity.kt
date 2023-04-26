package com.example.hirehub.ui.seeker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hirehub.HireHubApplication
import com.example.hirehub.databinding.ActivitySeekerHomeBinding
import com.example.hirehub.model.*
import com.example.hirehub.model.entities.OfferWithCategory
import com.example.hirehub.model.entities.User
import com.example.hirehub.ui.LoginActivity
import java.io.Serializable

class SeekerHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeekerHomeBinding

    private val offerViewModel: OfferViewModel by viewModels {
        OfferViewModelFactory((application as HireHubApplication).offerRepository)
    }

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as HireHubApplication).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeekerHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val currentUser = intent.getSerializableExtra("currentUser") as? User

        setSupportActionBar(binding.toolbar)

        val recyclerView = binding.rvView
        val adapter = OfferAdapter()
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                layoutManager.orientation
            )
        )

        currentUser?.let {
            userViewModel.findUser(it.userUsername).observe(this) { user ->
                if (user != null) currentUser.user_id = user.user_id
            }
        }

        offerViewModel.allOfferWithCategory.observe(this) { offers ->
//        offerViewModel.allOffers.observe(this) { offers ->
//            Log.d("SeekerHome", offers.get(0).categoryName + "\n" + offers.get(1).categoryName +
//                    "\n" + offers.get(2).categoryName + "\n" + offers.get(3).categoryName)
            offers.let { adapter.submitList(it) }
        }

        adapter.setOnClickListener(object :
            OfferAdapter.OnClickListener {
            override fun onClick(item: OfferWithCategory) {
                currentUser?.userUsername?.let {

                    val i = Intent(applicationContext, OfferDescriptionsActivity::class.java)
                    i.putExtra("Offer", item as Serializable)
                    i.putExtra("currentUser", currentUser as Serializable)
                    startActivity(i)

                }
            }
        })

        binding.tvLogin.setOnClickListener {
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)
        }

        binding.btnAccount.setOnClickListener {
            val i = Intent(applicationContext, AccountSeekerActivity::class.java)
            startActivity(i)
        }

        binding.btnSearch.setOnClickListener {
            val i = Intent(applicationContext, SearchActivity::class.java)
            startActivity(i)
        }

        binding.btnOffers.setOnClickListener {
            val i = Intent(applicationContext, SeekerRequestsActivity::class.java)
            i.putExtra("currentUser", currentUser as Serializable)
            startActivity(i)
        }
    }
}