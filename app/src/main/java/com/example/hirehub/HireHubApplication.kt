package com.example.hirehub

import android.app.Application
import com.example.hirehub.model.HireHubRoomDatabase
import com.example.hirehub.model.OfferRepository
import com.example.hirehub.model.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HireHubApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { HireHubRoomDatabase.getDatabase(this, applicationScope) }
    val offerRepository by lazy { OfferRepository(database.offerDao()) }
    val userRepository by lazy { UserRepository(database.userDao()) }
}