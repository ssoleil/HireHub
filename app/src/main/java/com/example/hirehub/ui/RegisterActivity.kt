package com.example.hirehub.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hirehub.HireHubApplication
import com.example.hirehub.databinding.ActivityRegisterBinding
import com.example.hirehub.model.UserViewModel
import com.example.hirehub.model.UserViewModelFactory
import com.example.hirehub.model.entities.User
import com.example.hirehub.ui.hr.HrHomeActivity
import com.example.hirehub.ui.seeker.SeekerHomeActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val TAG = "RegisterActivity"

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as HireHubApplication).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvLogin.setOnClickListener {
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)
        }

        binding.button.setOnClickListener {
            val name = binding.etName
            val username = binding.etUsername
            val pwd = binding.etPwd

            if (name.text.isEmpty()) {
                name.error = "Name is required!"
            } else if (username.text.isEmpty()) {
                username.error = "Username is required"
            } else if (pwd.text.isEmpty()) {
                pwd.error = "Password is required"
            } else {
                userViewModel.findUser(username.text.toString()).observe(this) { user ->
                    //if we don't have an account
                    if (user == null) {
                        val newUser: User = if (binding.swIsCompany.isChecked) {
                            User(
                                0,
                                name.text.toString(),
                                username.text.toString(),
                                pwd.text.toString(),
                                "hr",
                                null,
                                null,
                                null,
                                null
                            )
                        } else {
                            User(
                                0,
                                name.text.toString(),
                                username.text.toString(),
                                pwd.text.toString(),
                                "seeker",
                                null,
                                null,
                                null,
                                null
                            )
                        }
                        userViewModel.insert(newUser)

                        if (newUser.userStatus == "hr") {
                            val i = Intent(applicationContext, HrHomeActivity::class.java)
                            i.putExtra("currentUserId", newUser.userUsername)
                            startActivity(i)
                        } else {
                            val i = Intent(applicationContext, SeekerHomeActivity::class.java)
                            i.putExtra("currentUserId", newUser.userUsername)
                            startActivity(i)
                        }

                    } else {
                        Toast.makeText(this, "User is already exists", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}