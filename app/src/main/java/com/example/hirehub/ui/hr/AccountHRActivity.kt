package com.example.hirehub.ui.hr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hirehub.HireHubApplication
import com.example.hirehub.databinding.ActivityAccountHrBinding
import com.example.hirehub.model.OfferViewModel
import com.example.hirehub.model.OfferViewModelFactory
import com.example.hirehub.model.UserViewModel
import com.example.hirehub.model.UserViewModelFactory
import com.example.hirehub.ui.LoginActivity


class AccountHRActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountHrBinding
    private var currentUsername : String? = null

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as HireHubApplication).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountHrBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        currentUsername = intent.getStringExtra("currentUserId")

        currentUsername?.let {
            changeUserUI(it)
        }


        binding.btnCroissant.setOnClickListener {
            Toast.makeText(this, "You are a sweet croissant", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnEditCompany.setOnClickListener {
            //ask for the update
            val builder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val customView = inflater.inflate(com.example.hirehub.R.layout.update_company, null)

            builder.setView(customView)
                .setTitle("New Company Name")
                .setPositiveButton("Confirm") { dialog, id ->
                    //update
                    val etCompany = customView.findViewById(com.example.hirehub.R.id.et_update_company_name) as EditText
                    if (etCompany.text.isEmpty()) {
                        etCompany.error = "Company name is required!"
                    } else {
                        currentUsername?.let { it1 ->
                            userViewModel.findUser(it1).observe(this) { user ->
                                if (user != null) {
                                    user.userCompany = etCompany.text.toString()
                                    userViewModel.update(user)
                                }
                                binding.tvCompanyName.text = user?.userCompany
                            }
                        }

                    }
                }
                .setNegativeButton("Cancel") { dialog, id ->
                    dialog.cancel()
                }
            builder.create()
            builder.show()
        }

        binding.btnEditName.setOnClickListener {
            //ask for the update
            val builder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val customView = inflater.inflate(com.example.hirehub.R.layout.update_username, null)

            builder.setView(customView)
                .setTitle("New User Name")
                .setPositiveButton("Confirm") { dialog, id ->
                    //update
                    val etUsername = customView.findViewById(com.example.hirehub.R.id.et_update_username) as EditText
                    if (etUsername.text.isEmpty()) {
                        etUsername.error = "User name is required!"
                    } else {
                        currentUsername?.let { it1 ->
                            userViewModel.findUser(it1).observe(this) { user ->
                                if (user != null) {
                                    user.userName = etUsername.text.toString()
                                    userViewModel.update(user)
                                }
                                binding.tvName.text = user?.userName
                            }
                        }

                    }
                }
                .setNegativeButton("Cancel") { dialog, id ->
                    dialog.cancel()
                }
            builder.create()
            builder.show()

        }

        binding.btnLogOut.setOnClickListener {
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)
        }
    }

    private fun changeUserUI(it : String) {
        userViewModel.findUser(it).observe(this) { user ->
            binding.tvCompanyName.text = user?.userCompany
            binding.tvName.text = user?.userName
        }
    }
}