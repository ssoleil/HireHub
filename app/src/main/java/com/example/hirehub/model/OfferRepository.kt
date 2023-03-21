package com.example.hirehub.model

import androidx.annotation.WorkerThread
import com.example.hirehub.model.dao.OfferDao
import com.example.hirehub.model.entities.Offer
import kotlinx.coroutines.flow.Flow

class OfferRepository(private val offerDao: OfferDao) {

    val allOffers: Flow<List<Offer>> = offerDao.getAllOffer()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(offer: Offer) {
        offerDao.insert(offer)
    }
}