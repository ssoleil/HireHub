package com.example.hirehub.model

import androidx.lifecycle.*
import com.example.hirehub.model.entities.OfferWithCategory
import com.example.hirehub.model.entities.User
import com.example.hirehub.model.entities.relations.UserOfferPair
import com.example.hirehub.model.entities.relations.UserWithOffer
import com.example.hirehub.model.repository.UserRepository
import com.example.hirehub.model.repository.UserWithOfferRepository
import kotlinx.coroutines.launch

class UserWithOfferViewModel(private val userWithOfferRepository: UserWithOfferRepository) : ViewModel() {

    fun insert(join: UserWithOffer) = viewModelScope.launch {
        userWithOfferRepository.insert(join)
    }

    fun findUser(user_id: Int) : LiveData<UserOfferPair?> {
        return userWithOfferRepository.findOffersByUser(user_id)
    }

//    fun findUserOfferPairsByUser(hr_id : Int, hr_offer_ids: List<Int>?): LiveData<List<UserOfferPair>?> =
//        userWithOfferRepository.findUserOfferPairsByUser(hr_id, hr_offer_ids)

    fun deleteAll() = viewModelScope.launch {
        userWithOfferRepository.deleteAll()
    }
}

class UserWithOfferViewModelFactory(private val userWithOfferRepository: UserWithOfferRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserWithOfferViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserWithOfferViewModel(userWithOfferRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}