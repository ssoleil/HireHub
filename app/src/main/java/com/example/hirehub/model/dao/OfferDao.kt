package com.example.hirehub.model.dao

import androidx.room.*
import com.example.hirehub.model.entities.Offer
import kotlinx.coroutines.flow.Flow

@Dao
interface OfferDao {

    @Query("SELECT * FROM offer_table")
    fun getAllOffer(): Flow<List<Offer>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(offer: Offer)

    @Query("DELETE FROM offer_table")
    suspend fun deleteAll()
}