package com.example.hirehub.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.User
import com.example.hirehub.model.entities.relations.UserWithOffer
import kotlinx.coroutines.flow.Flow

@Dao
interface UserWithOfferDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(join: UserWithOffer)

    @Query("SELECT * FROM offer_table")
    fun getAllOffers(): Flow<List<Offer>>
}