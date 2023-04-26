package com.example.hirehub.ui.hr

import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.hirehub.HireHubApplication
import com.example.hirehub.R
import com.example.hirehub.databinding.ActivityCreateOfferBinding
import com.example.hirehub.model.*
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferCategory
import com.example.hirehub.model.entities.User
import com.example.hirehub.model.entities.relations.UserWithOffer
import com.google.android.material.internal.ViewUtils.hideKeyboard

class CreateOfferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateOfferBinding

    private val categoryViewModel: OfferCategoryViewModel by viewModels {
        OfferCategoryViewModelFactory((application as HireHubApplication).categoryRepository)
    }

    private val positionViewModel: PositionViewModel by viewModels {
        PositionViewModelFactory((application as HireHubApplication).positionRepository)
    }

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as HireHubApplication).userRepository)
    }

    private val userWithOfferViewModel: UserWithOfferViewModel by viewModels {
        UserWithOfferViewModelFactory((application as HireHubApplication).userWithOfferRepository)
    }

    private val offerViewModel: OfferViewModel by viewModels {
        OfferViewModelFactory((application as HireHubApplication).offerRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateOfferBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        categoryViewModel.allCategoryOffers.observe(this) {
            val categoriesStr = mutableListOf<String>()
            val categories = categoryViewModel.allCategoryOffers.value!!
            for (c in categories)
                categoriesStr.add(c.categoryName)

            val categoryArrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, categoriesStr)
            binding.dropCategory.setAdapter(categoryArrayAdapter)
            (binding.dropCategory as AutoCompleteTextView).inputType = InputType.TYPE_NULL
        }

        positionViewModel.allPositions.observe(this) {
            val positionsStr = mutableListOf<String>()
            val positions = positionViewModel.allPositions.value!!
            for (p in positions)
                positionsStr.add(p.positionName)

            val positionArrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, positionsStr)
            binding.dropPosition.setAdapter(positionArrayAdapter)
            (binding.dropPosition as AutoCompleteTextView).inputType = InputType.TYPE_NULL
        }


        binding.btnPost.setOnClickListener {
            val offerName = binding.etOfferName
            val location = binding.etLocation.text.toString()
            val salary = binding.etSalary.text.toString()
            val category_name = binding.dropCategory.text.toString()
            val position = binding.dropPosition.text.toString()
            val description = binding.etDescription.text.toString()

            if (offerName.text.isEmpty()) {
                offerName.error = "Offer name is required!"
//            } else if (!category.isSelected) {                //values might be null or ""
//                category.error = "Category is required"
//            } else if (location.text.isEmpty()) {
//                location.error = "Location is required"
//            } else if (!position.isSelected) {
//                position.error = "Position is required"
//            } else if (salary.text.isEmpty()) {
//                salary.error = "Salary is required"
            } else {
                val currentUser = intent.getSerializableExtra("currentUser") as? User

                categoryViewModel.findCategory(category_name).observe(this) { it ->
                    currentUser?.userUsername?.let { it1 ->
                        userViewModel.findUser(it1).observe(this) { user ->
                            if (it != null) {
                                Log.d("CreateOffer", it.categoryName)
                                val company = user?.userCompany ?: "Hidden company"
                                val offer = Offer(
                                    0, offerName.text.toString(), it.categoryId,
                                    company, salary, location, description, position, "active"
                                )

                                //change ui
                                offerViewModel.insert(offer)

                                //get the new offer id
                                offerViewModel.findOfferId(offer.offerName).observe(this) {
                                    Log.d("CreateOffer", "offer_id is" + it.toString())

                                    //make many-to-many-connection
                                    if (it != null) {
                                        val join = UserWithOffer(currentUser.user_id, it)
                                        userWithOfferViewModel.insert(join)
                                        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Log.d("CreateOffer", "offer_id == null")
                                        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } else
                                Log.d("CreateOffer", "it null")
                        }
                    }
                }
                finish()
            }
        }

    }

}