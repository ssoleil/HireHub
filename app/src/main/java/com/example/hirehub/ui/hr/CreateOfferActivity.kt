package com.example.hirehub.ui.hr

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.hirehub.HireHubApplication
import com.example.hirehub.R
import com.example.hirehub.databinding.ActivityCreateOfferBinding
import com.example.hirehub.model.*
import com.example.hirehub.model.entities.OfferCategory

class CreateOfferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateOfferBinding

    private val categoryViewModel: OfferCategoryViewModel by viewModels {
        OfferCategoryViewModelFactory((application as HireHubApplication).categoryRepository)
    }

    private val positionViewModel: PositionViewModel by viewModels {
        PositionViewModelFactory((application as HireHubApplication).positionRepository)
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
        }

        positionViewModel.allPositions.observe(this) {
            val positionsStr = mutableListOf<String>()
            val positions = positionViewModel.allPositions.value!!
            for (p in positions)
                positionsStr.add(p.positionName)

            val positionArrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, positionsStr)
            binding.dropPosition.setAdapter(positionArrayAdapter)
        }


        binding.btnPost.setOnClickListener {
            val offerName = binding.etOfferName
            val location = binding.etLocation
            val salary = binding.etSalary
            val category = binding.dropCategory
            val position = binding.dropPosition

//            if (offerName.text.isEmpty()) {
//                offerName.error = "Offer name is required!"
//            } else if (location.text.isEmpty()) {
//                location.error = "Location is required"
//            } else if (salary.text.isEmpty()) {
//                salary.error = "Salary is required"
//            } else {
        }
    }

}