package com.example.hirehub.model.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.hirehub.model.dao.OfferDao
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferWithCategory
import com.example.hirehub.model.entities.User
//import com.example.hirehub.model.entities.OfferWithCategoryOffer
import kotlinx.coroutines.flow.Flow

class OfferRepository(private val offerDao: OfferDao) {

//    val allOffers: Flow<List<Offer>> = offerDao.getAllOffer()
    val allOffersWithCategories: Flow<List<OfferWithCategory>> = offerDao.getAllOfferWithCategory()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(offer: Offer) {
        offerDao.insert(offer)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        offerDao.deleteAll()
    }

    fun findOfferId(name: String) : LiveData<Int?> =
        offerDao.findOfferByName(name)
}