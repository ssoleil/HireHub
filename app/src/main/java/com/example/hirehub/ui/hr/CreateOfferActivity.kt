package com.example.hirehub.ui.hr

import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
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
            val category_id = binding.dropCategory.id+1
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

                //todo: find category id, not String
                val company = currentUser?.userCompany ?: ""
                val offer = Offer(0, offerName.text.toString(), category_id,
                    company, salary, location, description, position, "active")

                //todo: add to my offers
                Log.d("CreateOffer", offer.offerName + "-" + offer.offerCity + "-" + offer.offerCategoryId +
                        "-" + offer.offerCompanyName + "-" + offer.offerPosition + "-" + offer.offerDescription)

                //todo: change ui
                offerViewModel.insert(offer)
                finish()
            }
        }

    }

}