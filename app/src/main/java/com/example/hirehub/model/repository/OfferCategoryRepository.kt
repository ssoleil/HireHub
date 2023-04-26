package com.example.hirehub.model.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.hirehub.model.dao.OfferCategoryDao
import com.example.hirehub.model.dao.OfferDao
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferCategory
import com.example.hirehub.model.entities.User
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

    fun findCategory(name: String) : LiveData<OfferCategory?> =
        offerCategoryDao.findCategoryByName(name)
}