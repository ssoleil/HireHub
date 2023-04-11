package com.example.hirehub.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hirehub.model.entities.Education
import kotlinx.coroutines.flow.Flow

@Dao
interface EducationDao {

    @Query("SELECT * FROM education_table")
    fun getAllEducations(): Flow<List<Education>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(education: Education)

    @Query("DELETE FROM education_table")
    suspend fun deleteAll()
}