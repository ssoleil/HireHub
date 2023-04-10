package com.example.hirehub.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.Position
import kotlinx.coroutines.flow.Flow

@Dao
interface PositionDao {

    @Query("SELECT * FROM position_table")
    fun getAllPositions(): Flow<List<Position>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(position: Position)

    @Query("DELETE FROM position_table")
    suspend fun deleteAll()
}