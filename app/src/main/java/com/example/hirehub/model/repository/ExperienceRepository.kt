package com.example.hirehub.model.repository

import androidx.annotation.WorkerThread
import com.example.hirehub.model.dao.ExperienceDao
import com.example.hirehub.model.dao.OfferCategoryDao
import com.example.hirehub.model.dao.OfferDao
import com.example.hirehub.model.entities.Experience
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferCategory
import kotlinx.coroutines.flow.Flow

class ExperienceRepository(private val experienceDao: ExperienceDao) {

    val allExperience: Flow<List<Experience>> = experienceDao.getAllExperience()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(experience: Experience) {
        experienceDao.insert(experience)
    }
}