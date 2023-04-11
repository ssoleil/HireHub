package com.example.hirehub.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hirehub.model.entities.Experience
import kotlinx.coroutines.flow.Flow

@Dao
interface ExperienceDao {

    @Query("SELECT * FROM experience_table")
    fun getAllExperience(): Flow<List<Experience>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(education: Experience)

    @Query("DELETE FROM experience_table")
    suspend fun deleteAll()
}