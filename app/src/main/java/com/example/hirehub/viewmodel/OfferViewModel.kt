package com.example.hirehub.model

import androidx.lifecycle.*
import com.example.hirehub.model.entities.Offer
//import com.example.hirehub.model.entities.OfferWithCategoryOffer
import kotlinx.coroutines.launch

class OfferViewModel(private val offerRepository: OfferRepository) : ViewModel() {

    val allOffers: LiveData<List<Offer>> = offerRepository.allOffers.asLiveData()
//    val allOfferWithCategoryOffer: LiveData<List<OfferWithCategoryOffer>> = offerRepository.allOffersWithCategories.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(offer: Offer) = viewModelScope.launch {
        offerRepository.insert(offer)
    }

    fun deleteAll() = viewModelScope.launch {
        offerRepository.deleteAll()
    }
}

class OfferViewModelFactory(private val offerRepository: OfferRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfferViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OfferViewModel(offerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}