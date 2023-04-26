package com.example.hirehub.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferCategory
import com.example.hirehub.model.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface OfferCategoryDao {

    @Query("SELECT * FROM offer_category_table")
    fun getAllOfferCategory(): Flow<List<OfferCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(offerCategory: OfferCategory)

    @Query("SELECT * FROM offer_category_table WHERE category_name LIKE :name")
    fun findCategoryByName(name: String): LiveData<OfferCategory?>

    @Query("DELETE FROM offer_category_table")
    suspend fun deleteAll()
}