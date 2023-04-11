package com.example.hirehub.model.repository

import androidx.annotation.WorkerThread
import com.example.hirehub.model.dao.EducationDao
import com.example.hirehub.model.dao.OfferCategoryDao
import com.example.hirehub.model.dao.OfferDao
import com.example.hirehub.model.entities.Education
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferCategory
import kotlinx.coroutines.flow.Flow

class EducationRepository(private val educationDao: EducationDao) {

    val allEducation: Flow<List<Education>> = educationDao.getAllEducations()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(education: Education) {
        educationDao.insert(education)
    }
}