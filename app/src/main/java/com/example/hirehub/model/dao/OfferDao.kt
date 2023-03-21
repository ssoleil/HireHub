package com.example.hirehub.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hirehub.model.entities.Offer
import kotlinx.coroutines.flow.Flow

@Dao
interface OfferDao {

    @Query("SELECT * FROM offer_table")
    fun getAllOffer(): Flow<List<Offer>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(offer: Offer)
}