package com.example.hirehub.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferWithCategory
import com.example.hirehub.model.entities.User
//import com.example.hirehub.model.entities.OfferWithCategoryOffer
import kotlinx.coroutines.flow.Flow

@Dao
interface OfferDao {
    @Query("SELECT * FROM offer_table")
    fun getAllOffer(): Flow<List<Offer>>

    @Query("SELECT * FROM offer_table INNER JOIN " +
            "offer_category_table ON offer_category_table" +
            ".categoryId = offer_table.offer_category_id")
    fun getAllOfferWithCategory(): Flow<List<OfferWithCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(offer: Offer)

    @Query("SELECT offer_id FROM offer_table " +
            "WHERE offer_name LIKE :name")
    fun findOfferByName(name: String): LiveData<Int?>

    @Query("DELETE FROM offer_table")
    suspend fun deleteAll()
}