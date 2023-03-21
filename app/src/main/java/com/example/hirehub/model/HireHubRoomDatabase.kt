package com.example.hirehub.model;


import android.content.Context
import kotlin.jvm.Volatile;

import androidx.room.Database
import androidx.room.Room;
import androidx.room.RoomDatabase
import com.example.hirehub.model.dao.OfferDao
import com.example.hirehub.model.dao.UserDao
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.User

// Each entity corresponds to a table that will be created in the database

@Database(entities = arrayOf(User::class, Offer::class), version = 1)
abstract class HireHubRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun offerDao(): OfferDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: HireHubRoomDatabase? = null

        fun getDatabase(context: Context): HireHubRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        HireHubRoomDatabase::class.java,
                        "hirehub_database"
                    ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
