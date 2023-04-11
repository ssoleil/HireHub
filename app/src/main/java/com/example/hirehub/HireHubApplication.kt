package com.example.hirehub

import android.app.Application
import com.example.hirehub.model.*
import com.example.hirehub.model.repository.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HireHubApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { HireHubRoomDatabase.getDatabase(this, applicationScope) }

    val offerRepository by lazy { OfferRepository(database.offerDao()) }
    val userRepository by lazy { UserRepository(database.userDao()) }
    val userWithOfferRepository by lazy { UserWithOfferRepository(database.userWithOfferDao()) }
    val categoryRepository by lazy { OfferCategoryRepository(database.categoryDao()) }
    val positionRepository by lazy { PositionRepository(database.positionDao()) }
    val skillRepository by lazy { SkillRepository(database.skillDao()) }
    val experienceRepository by lazy { ExperienceRepository(database.experienceDao()) }
    val educationRepository by lazy { EducationRepository(database.educationDao()) }
}