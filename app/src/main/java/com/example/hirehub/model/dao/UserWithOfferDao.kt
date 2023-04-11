package com.example.hirehub.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.User
import com.example.hirehub.model.entities.relations.UserOfferPair
import com.example.hirehub.model.entities.relations.UserWithOffer
//import com.example.hirehub.model.entities.relations.UserWithOffer
import kotlinx.coroutines.flow.Flow

@Dao
interface UserWithOfferDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(join: UserWithOffer)

//    @Query("SELECT * FROM user_offer_table")
//    fun getAllUserOffers(): Flow<List<UserOfferPair>>

    @Query("SELECT * FROM user_table WHERE user_id LIKE :user_id")
    fun findOffersByUser(user_id: Int): LiveData<UserOfferPair?>

    @Query("DELETE FROM user_offer_table")
    suspend fun deleteAll()
}