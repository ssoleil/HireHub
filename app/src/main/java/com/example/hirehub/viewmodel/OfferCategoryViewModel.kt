package com.example.hirehub.model

import androidx.lifecycle.*
import com.example.hirehub.model.entities.OfferCategory
import com.example.hirehub.model.repository.OfferCategoryRepository
import kotlinx.coroutines.launch

class OfferCategoryViewModel(private val offerCategoryRepository: OfferCategoryRepository) : ViewModel() {

    val allCategoryOffers: LiveData<List<OfferCategory>> = offerCategoryRepository.allOfferCategories.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(offerCategory: OfferCategory) = viewModelScope.launch {
        offerCategoryRepository.insert(offerCategory)
    }

    fun deleteAll() = viewModelScope.launch {
        offerCategoryRepository.deleteAll()
    }
}

class OfferCategoryViewModelFactory(private val offerCategoryRepository: OfferCategoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfferCategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OfferCategoryViewModel(offerCategoryRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}