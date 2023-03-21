package com.example.hirehub.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hirehub.model.entities.Offer

@Dao
interface OfferDao {

    @Query("SELECT * FROM offer_table")
    fun getAllOffer(): List<Offer>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(offer: Offer)
}