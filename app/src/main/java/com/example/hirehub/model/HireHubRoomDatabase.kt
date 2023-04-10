package com.example.hirehub.model;


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hirehub.model.dao.*
import com.example.hirehub.model.entities.*
import com.example.hirehub.model.dao.OfferCategoryDao
import com.example.hirehub.model.dao.OfferDao
import com.example.hirehub.model.dao.PositionDao
import com.example.hirehub.model.dao.UserDao
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferCategory
import com.example.hirehub.model.entities.Position
import com.example.hirehub.model.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Each entity corresponds to a table that will be created in the database

@Database(entities = [User::class, Offer::class, OfferCategory::class,
    Position::class, Education::class, Experience::class, Skill::class], version = 6)
abstract class HireHubRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun offerDao(): OfferDao
    abstract fun categoryDao(): OfferCategoryDao
    abstract fun positionDao(): PositionDao
    abstract fun skillDao(): SkillDao
    abstract fun experienceDao(): ExperienceDao
    abstract fun educationDao(): EducationDao


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
                    //.addCallback(OfferDatabaseCallback(scope))
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
                        populateDatabase(database.offerDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * Start app with DB with several offers
         */
        suspend fun populateDatabase(offerDao: OfferDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            offerDao.deleteAll()

            var offer = Offer(1, "Offer One", 1, "DreamCompany",
            "200$", "Grenoble", "This is the long description of the first offer. " +
                        "We suggest you a great opportunity to become a product manager in our marvellous company. " +
                        "Your tasks are: task1, task2, task3...", "Junior", "active")
            offerDao.insert(offer)

        }
    }
}
