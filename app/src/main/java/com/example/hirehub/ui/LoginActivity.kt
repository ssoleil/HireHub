package com.example.hirehub.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hirehub.HireHubApplication
import com.example.hirehub.databinding.ActivityLoginBinding
import com.example.hirehub.model.*
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferCategory
import com.example.hirehub.model.entities.Position
import com.example.hirehub.model.entities.User
import com.example.hirehub.ui.hr.HrHomeActivity
import com.example.hirehub.ui.seeker.SeekerHomeActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as HireHubApplication).userRepository)
    }

    private val offerViewModel: OfferViewModel by viewModels {
        OfferViewModelFactory((application as HireHubApplication).offerRepository)
    }

    private val categoryViewModel: OfferCategoryViewModel by viewModels {
        OfferCategoryViewModelFactory((application as HireHubApplication).categoryRepository)
    }

    private val positionViewModel: PositionViewModel by viewModels {
        PositionViewModelFactory((application as HireHubApplication).positionRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {

            val username = binding.etUsername
            val pwd = binding.etPwd

            if (username.text.isEmpty())
                username.error = "Username is required!"

            else if (pwd.text.isEmpty())
                pwd.error = "Password is required"

            else {
                userViewModel.findUser(username.text.toString(), pwd.text.toString()).observe(this) { user ->
                    //if we don't have an account
                    when {
                        user == null -> {
                            Toast.makeText(this, "User is not found", Toast.LENGTH_SHORT).show()
                        }
                        user.userStatus == "seeker" -> {
                            userViewModel.currentUser = user
                            val i = Intent(applicationContext, SeekerHomeActivity::class.java)
                            i.putExtra("currentUserId", user.userUsername)
                            startActivity(i)
                        }
                        user.userStatus == "hr" -> {
                            userViewModel.currentUser = user
                            Log.d("currentUserId", user.userUsername)
                            val i = Intent(applicationContext, HrHomeActivity::class.java)
                            i.putExtra("currentUserId", user.userUsername)
                            startActivity(i)
                        }
                    }
                }
            }
        }

        binding.tvRegister.setOnClickListener {
            val i = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(i)
        }

        binding.btnDb.setOnClickListener {

            setUpDB()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.d("HrAccount", "Update ${userViewModel.currentUser?.userUsername}")
        outState.putCharSequence("currentUsername", userViewModel.currentUser?.userUsername)
    }

    private fun setUpDB() {
        //userViewModel.deleteAll()

        userViewModel.insert(User(0, "test", "test",
            "test", "seeker", null, null, null,
            null))

        userViewModel.insert(User(0, "hr", "hr",
            "hr", "hr", null, null, null,
            null))

        categoryViewModel.insert(OfferCategory(0, "programming"))
        categoryViewModel.insert(OfferCategory(0, "management"))
        categoryViewModel.insert(OfferCategory(0, "languages"))
        categoryViewModel.insert(OfferCategory(0, "mathematics"))
        categoryViewModel.insert(OfferCategory(0, "design"))
        categoryViewModel.insert(OfferCategory(0, "sales"))
        categoryViewModel.insert(OfferCategory(0, "banking"))
        categoryViewModel.insert(OfferCategory(0, "finances"))
        categoryViewModel.insert(OfferCategory(0, "smm"))

        positionViewModel.insert(Position(0, "Intern"))
        positionViewModel.insert(Position(0, "Junior"))
        positionViewModel.insert(Position(0, "Middle"))
        positionViewModel.insert(Position(0, "Senior"))
        positionViewModel.insert(Position(0, "Team Lead"))
        positionViewModel.insert(Position(0, "Department Head"))
        positionViewModel.insert(Position(0, "Regular Employee"))
        positionViewModel.insert(Position(0, "CEO"))
        positionViewModel.insert(Position(0, "Financial Director"))

        var offer = Offer(1, "Offer One", "Management", "DreamCompany",
            "200$", "Grenoble", "This is the long description of the first offer. " +
                    "We suggest you a great opportunity to become a product manager in our marvellous company. " +
                    "Your tasks are: task1, task2, task3...", "Junior", "active")
        offerViewModel.insert(offer)

        offer = Offer(2, "Offer Two", "Programming", "GreatSolutions",
            "900$", "Paris", "We need to write something here for the second offer. " +
                    "We offer you a nice chance to become a SQL Programmer in our marvellous company. " +
                    "Required skills are: SQL, PHP, Agile...", "Middle", "active")
        offerViewModel.insert(offer)

        offer = Offer(3, "Nice offer!!!", "Programming", "GameTech",
            "1800$", "Bern", "Description of the third offer. " +
                    "It's a nice chance to become a Java Programmer in our marvellous company. " +
                    "Required skills are: Java 5+ years, PHP, Agile...", "Senior", "active")
        offerViewModel.insert(offer)

        offer = Offer(4, "Hurry to become our employee", "Business", "SmartBusiness",
            "667$", "London", "Take this option if you are a shark " +
                    "You are out best candidate if you know: Math, Probability, Law...", "Intern", "active")
        offerViewModel.insert(offer)
    }
}