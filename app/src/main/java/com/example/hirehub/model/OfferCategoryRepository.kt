package com.example.hirehub.model

import androidx.annotation.WorkerThread
import com.example.hirehub.model.dao.OfferCategoryDao
import com.example.hirehub.model.dao.OfferDao
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferCategory
import kotlinx.coroutines.flow.Flow

class OfferCategoryRepository(private val offerCategoryDao: OfferCategoryDao) {

    val allOfferCategories: Flow<List<OfferCategory>> = offerCategoryDao.getAllOfferCategory()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(offerCategory: OfferCategory) {
        offerCategoryDao.insert(offerCategory)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        offerCategoryDao.deleteAll()
    }
}