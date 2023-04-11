package com.example.hirehub.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hirehub.model.entities.Skill
import kotlinx.coroutines.flow.Flow

@Dao
interface SkillDao {

    @Query("SELECT * FROM skill_table")
    fun getAllSkills(): Flow<List<Skill>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(skill: Skill)

    @Query("DELETE FROM skill_table")
    suspend fun deleteAll()
}