package com.example.hirehub.model;


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hirehub.model.dao.OfferDao
import com.example.hirehub.model.dao.UserDao
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Each entity corresponds to a table that will be created in the database

@Database(entities = [User::class, Offer::class], version = 1)
abstract class HireHubRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun offerDao(): OfferDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: HireHubRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): HireHubRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        HireHubRoomDatabase::class.java,
                        "hirehub_database"
                    )
                    .fallbackToDestructiveMigration()
                    .addCallback(OfferDatabaseCallback(scope))
                    //.addCallback(UserDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class OfferDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.offerDao(),database.userDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * Start app with DB with several offers
         */
        suspend fun populateDatabase(offerDao: OfferDao, userDao: UserDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            offerDao.deleteAll()

            val user = User(1, "test", "test","test", "seeker", 18,
                null, "I'm a seeker", null)
            userDao.insert(user)

            var offer = Offer(1, "Offer One", "Management", "DreamCompany",
            "200$", "Grenoble", "This is the long description of the first offer. " +
                        "We suggest you a great opportunity to become a product manager in our marvellous company. " +
                        "Your tasks are: task1, task2, task3...", "Junior", "active")
            offerDao.insert(offer)

            offer = Offer(2, "Offer Two", "Programming", "GreatSolutions",
                "900$", "Paris", "We need to write something here for the second offer. " +
                        "We offer you a nice chance to become a SQL Programmer in our marvellous company. " +
                        "Required skills are: SQL, PHP, Agile...", "Middle", "active")
            offerDao.insert(offer)

            offer = Offer(3, "Nice offer!!!", "Programming", "GameTech",
                "1800$", "Bern", "Description of the third offer. " +
                        "It's a nice chance to become a Java Programmer in our marvellous company. " +
                        "Required skills are: Java 5+ years, PHP, Agile...", "Senior", "active")
            offerDao.insert(offer)

            offer = Offer(4, "Hurry to become our employee", "Business", "SmartBusiness",
                "667$", "London", "Take this option if you are a shark " +
                        "You are out best candidate if you know: Math, Probability, Law...", "Intern", "active")
            offerDao.insert(offer)
        }

//        private class UserDatabaseCallback(
//            private val scope: CoroutineScope
//        ) : RoomDatabase.Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                // If you want to keep the data through app restarts,
//                // comment out the following line.
//                INSTANCE?.let { database ->
//                    scope.launch(Dispatchers.IO) {
//                        populateDatabase(database.userDao())
//                    }
//                }
//            }
//        }
//
//        /**
//         * Populate the database in a new coroutine.
//         * Start app with DB with several offers
//         */
//        suspend fun populateDatabase( userDao: UserDao) {
//
//            val user = User(1, "test", "test","test", "seeker", 18,
//                null, "I'm a seeker", null)
//            userDao.insert(user)
//
//        }
    }
}
